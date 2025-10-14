package pluralsight;

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
    public static void AddDeposit(){
        ArrayList<Transaction> depositList = new ArrayList<>();
        System.out.print("what is this deposit for? ");
        String discription = scanner.nextLine();
        System.out.print("Who is making the deposit? ");
        String vendor = scanner.nextLine();
        double amount = readDouble(scanner, "Enter the deposit amount: ");
        amount = validateAmount(amount,true);
        Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(),discription,vendor,amount);
        depositList.add(0,transaction);
        writeFile(transaction);
    }

    // prevent from crashing the app  on string input
    public static double readDouble(Scanner scanner1 , String message){
        while (true){
            System.out.print(message);
            String input = scanner1.nextLine();
            try{
                return Double.parseDouble(input);
            }catch (NumberFormatException e){
                System.out.println("❌ Invalid number! Please enter a valid value.");
            }
        }
    }

    public static double validateAmount(double amount, boolean shouldBePositive){
        while (true){
            if(shouldBePositive && amount > 0){
                return amount;
            } else if (!shouldBePositive && amount < 0) {
                return amount;
            }else{
                System.out.println(shouldBePositive?"❌ Invalid input. Please enter a positive amount. ":"❌ Invalid input. Please enter a negative amount. ");
                System.out.print("Enter amount again: ");
                amount = scanner.nextDouble();
            }
        }
    }

    public static void makePayment(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        System.out.print("what is the payment for? ");
        String description = scanner.nextLine();
        System.out.print("Who is being paid? ");
        String vendor = scanner.nextLine();
        double amount = readDouble(scanner,"How much is being paid? (please enter a negative amount) ");
        amount = validateAmount(amount,false);
        Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(),description,vendor,amount);
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
