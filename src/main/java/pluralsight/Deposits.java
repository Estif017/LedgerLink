package pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Deposits {
    static Scanner scanner = new Scanner(System.in);
    public static void AddDeposit(){
        ArrayList<Transaction> depositList = new ArrayList<>();

//        Ask for description and vendor
        System.out.print("what is this deposit for? ");
        String description = scanner.nextLine();
        System.out.print("Who is making the deposit? ");
        String vendor = scanner.nextLine();

        //reads and validate amount
        double amount = readDouble(scanner, "Enter the deposit amount: ");
        amount = validateAmount(amount,true);

        //create and added to the transaction
        Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(),description,vendor,amount);//save it to teh list
        depositList.add(0,transaction);
        writeFile(transaction);
    }

    // Read double value from user safely
    public static double readDouble(Scanner scanner1 , String message){
        //keeps prompting until a valid input
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

    //Validates amount to be positive or negative based on shouldBePositive.
    public static double validateAmount(double amount, boolean shouldBePositive){
        while (true){
            if(shouldBePositive && amount > 0){//verifies if deposit and mount is not negative
                return amount;
            } else if (!shouldBePositive && amount < 0) {//verifies if payment and amount is not positive
                return amount;
            }else{
                System.out.println(shouldBePositive?"❌ Invalid input. Please enter a positive amount. ":"❌ Invalid input. Please enter a negative amount. ");
                amount = readDouble(scanner, "Enter amount again: ");;
            }
        }
    }

    /**
     * Prompt user and make a payment (negative amount required).
     */
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

    //Writing to file
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
