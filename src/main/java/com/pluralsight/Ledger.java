package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    static Scanner scanner= new Scanner(System.in);
    public static void legerOperation(){
        HomeScreen.ledgerMenu();
        System.out.println("Which ledger would you like to view?");
        char leagerOption = scanner.next().charAt(0);
        switch (leagerOption){
            case 'a':
            case 'A':
                listAllTransactions();
                break;
            case 'd':
            case 'D':
                listAllDeposits();
                break;
            case 'p':
            case 'P':
                listAllPayments();
                break;
            case 'r':
            case 'R':
                Reports.reportsOperation();
                break;
            case 'h':
            case 'H':
                HomeScreen.showHomeScreen();
                break;
            default:
                System.out.println("You entered a wrong input!");
                break;
        }
    }

    public static void listAllTransactions(){
        List<Transaction>transactions = readTransactionCsv();
        for(Transaction transaction:transactions){
            System.out.println(transaction);
        }
    }

    public static void listAllDeposits(){
        List<Transaction>transactions = readTransactionCsv();
        for(Transaction transaction:transactions){
            if(transaction.getAmount()>0){
                System.out.println(transaction);
            }
        }
    }

    public static void listAllPayments(){
        List<Transaction>transactions = readTransactionCsv();
        for(Transaction transaction:transactions){
            if(transaction.getAmount()<0){
                System.out.println(transaction);
            }
        }
    }

    public static List<Transaction> readTransactionCsv(){
        List<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try{
            File fileLocation = new File("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line;
            reader.readLine();
            while ((line = reader.readLine())!=null){
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if(parts.length==5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1],timeFormatter);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    Transaction transaction = new Transaction(date,time,description,vendor,amount);
                    transactions.add(0,transaction);
                }

            }
            reader.close();
            return transactions;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
