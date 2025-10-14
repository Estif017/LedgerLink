package pluralsight;

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

        boolean running = true;
        while (running){
            HomeScreen.ledgerMenu();
            System.out.println("Which ledger would you like to view?");
            String input = scanner.nextLine().trim();
            LegerMenuOptions options = LegerMenuOptions.fromString(input);

            if(options==null){
                System.out.println("Invalid option. Please try again!");
                continue;
            }
            switch (options){
                case ALL -> listAllTransactions();
                case DEPOSITS -> listAllDeposits();
                case PAYMENTS -> listAllPayments();
                case REPORT -> Reports.reportsOperation();
                case HOME -> Main.mainMenu();
                case EXIT -> {
                    running = false;
                    System.out.println("Thank you for using LedgerLink, Good bye!!!");
                }

            }
        }
    }

    public static void listAllTransactions(){
        List<Transaction>transactions = readTransactionCsv();

        HomeScreen.sortingMenu();
        String input = scanner.nextLine();

        SortOption  sortOption = SortOption.formString(input);
        if(sortOption==null) sortOption = SortOption.NEWEST;
        Sorting.sortTransaction(transactions,sortOption);

        System.out.println(Transaction.header());
        for(Transaction transaction:transactions){
            System.out.println(transaction);
        }

        legerOperation();
    }

    public static void listAllDeposits(){
        List<Transaction>transactions = readTransactionCsv();
        System.out.println(Transaction.header());
        for(Transaction transaction:transactions){
            if(transaction.getAmount()>0){
                System.out.println(transaction);
            }
        }
        legerOperation();
    }

    public static void listAllPayments(){
        List<Transaction>transactions = readTransactionCsv();
        System.out.println(Transaction.header());
        for(Transaction transaction:transactions){
            if(transaction.getAmount()<0){
                System.out.println(transaction);
            }
        }
        legerOperation();
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
