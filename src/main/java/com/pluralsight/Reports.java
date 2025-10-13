package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Reports {
    LocalDate today = LocalDate.now();
    static Scanner scanner= new Scanner(System.in);
    public static void reportsOperation(){
        HomeScreen.reportsMenu();
        System.out.println("Which report would you like to view?");
        int reportsOperation = scanner.nextInt();
        scanner.nextLine();
        switch (reportsOperation){
            case 0:
                HomeScreen.ledgerMenu();
                break;
            case 1:
                monthToDate("2025-10");
                break;
            case 2:
                monthToDate("2025-09");
                break;
            case 3:
                monthToDate("2025");
                break;
            case 4:
                monthToDate("2024");
                break;
            case 5:
                searchByVendor();
            default:
                System.out.println("You entered a wrong input!");
                break;
        }
    }

    public static void monthToDate(String date){
        List<Transaction> transactions = Ledger.readTransactionCsv();
        for(Transaction transaction:transactions){
            if(transaction.getDate().toString().startsWith(date)){
                System.out.println(transaction);
            }
        }
        reportsOperation();
    }

    public static void searchByVendor(){
        System.out.println("Please enter the vendor name");
        String vendor = scanner.nextLine();
        List<Transaction> transactions = Ledger.readTransactionCsv();
        int counter= 0;
        for(Transaction transaction:transactions){
            if(transaction.getVendor().toLowerCase().startsWith(vendor)){
                System.out.println(transaction);
                counter++;
            }
        }
        if(counter==0){
            System.out.println("Sorry couldn't find "+vendor+" vendor");
        }
        reportsOperation();
    }
}
