package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Diposits {
    static Scanner scanner = new Scanner(System.in);
    public static void AddDiposit(){
        ArrayList<Transaction> depositList = new ArrayList<>();
        System.out.print("what is this deposit for? ");
        String discription = scanner.nextLine();
        System.out.print("Who is making the deposit? ");
        String vendor = scanner.nextLine();
        System.out.print("Enter the deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(),discription,vendor,amount);
        depositList.add(0,transaction);
        writeFile(transaction);
    }

    public static void makePayment(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        System.out.print("what is the payment for? ");
        String desctiption = scanner.nextLine();
        System.out.print("Who is being paid? ");
        String vendor = scanner.nextLine();
        System.out.print("How much is being paid? (please enter a negative amount) ");
        double amount = scanner.nextDouble();
        boolean amountValidation;
        if(amount>0){
            amountValidation = false;
            while (!amountValidation){
                System.out.println("Debt can't be a postive number please enter a negative amount");
                System.out.print("How much is being paid? (please enter a negative amount) ");
                 amount = scanner.nextDouble();
                 if(amount<0){
                     amountValidation=true;
                 }
            }
        }
        scanner.nextLine();
        Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(),desctiption,vendor,amount);
        transactions.add(0,transaction);
        writeFile(transaction);

    }

    public static void writeFile( Transaction transaction){
        try {
            File fileLocation = new File("src/main/resources/transactions.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation,true));
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String line = String.format("%s|%s|%s|%s|%.2f",transaction.getDate(),
                    transaction.getTime().format(timeFormatter),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
            writer.write(line);
            writer.newLine();
            writer.close();
            System.out.println("Successfully added to the Transaction record!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
