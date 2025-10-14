package pluralsight;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        HomeScreen.welcomeMessage();
        mainMenu();
    }
    public static void mainMenu(){
        boolean running = true;
        while(running){
            HomeScreen.showHomeScreen();
            String input = scanner.nextLine().trim();
            MainMenuOptions option = MainMenuOptions.fromString(input);
            if(option==null){
                System.out.println("Invalid option. Please try again!");
                continue;
            }
            switch (option){
                case DEPOSIT -> Diposits.AddDeposit();
                case PAYMENT -> Diposits.makePayment();
                case LEDGER -> Ledger.legerOperation();
                case EXIT -> {
                    running = false;
                    System.out.println("Thank you for using LedgerLink, Good bye!!!");
                }
            }
        }
    }
}