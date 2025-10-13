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
                break;
            case 6:
                customSearch();
                break;
            case 7:
                searchByAmount();
                break;
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

    public static void customSearch(){
        System.out.println("Please enter the key word");
        String keyword = scanner.nextLine();
        List<Transaction> transactions = Ledger.readTransactionCsv();
        int counter= 0;
        for(Transaction transaction:transactions){
            if(transaction.getVendor().trim().toLowerCase().contains(keyword) || transaction.getDescription().trim().toLowerCase().contains(keyword)||transaction.getDate().toString().trim().toLowerCase().contains(keyword)){
                System.out.println(transaction);
                counter++;
            }
        }
        if(counter==0){
            System.out.println("Sorry couldn't find "+keyword+" vendor");
        }
        reportsOperation();
    }

    public static void searchByAmount(){
        System.out.println("Enter the minimum amount (press Enter if no minimum amount):");
        String minimumInput = scanner.nextLine();

        System.out.println("Enter the maximum amount (press Enter if no maximum amount):");
        String maximumInput = scanner.nextLine();

        List<Transaction> transactions = Ledger.readTransactionCsv();

        Double minimum = null;
        Double maximum = null;

        if(!minimumInput.isEmpty()){
            minimum = Double.parseDouble(minimumInput);
        }
        if(!maximumInput.isEmpty()){
            maximum = Double.parseDouble(maximumInput);
        }

        System.out.println("\n--- Filtered Transactions by Amount ---");

        for(Transaction transaction : transactions){
            boolean withinMin = (minimum==null || transaction.getAmount()>=minimum);
            boolean withinMax =  (maximum==null || transaction.getAmount()<= maximum);

            if(withinMin && withinMax){
                System.out.println(transaction);
            }
        }
        reportsOperation();
    }
}
