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
            char option = scanner.next().charAt(0);
            switch (option){
                case 'D':
                case 'd':
                    Diposits.AddDeposit();
                    break;
                case 'P':
                case 'p':
                    Diposits.makePayment();
                    break;
                case 'L':
                case 'l':
                    Ledger.legerOperation();
                    break;
                case'X':
                case'x':
                    running=false;
                    System.out.println("Thank you for using LedgerLink, Good bye!!! ");
                    break;
                default:
                    System.out.println("You entered a wrong input!");
                    break;
            }
        }
    }
}