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
        File fileLocation = new File("src/main/resources/transactions.csv");
        writeFile(String.valueOf(fileLocation),transaction);
        System.out.println("Successfully added the product!!!");
    }

    public static void writeFile(String fileLocation, Transaction transaction){
        try {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
