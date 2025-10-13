package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomeScreen.welcomeMessage();
        boolean running = true;
        while(running){
            HomeScreen.showHomeScreen();
            char option = scanner.next().charAt(0);
            switch (option){
                case 'D':
                case 'd':
                    Diposits.AddDiposit();
                    break;
                case 'P':
                case 'p':
                case 'L':
                case 'l':
                    Ledger.legerOperation();
                    break;
                case'X':
                    running=false;
                    break;
                default:
                    System.out.println("You entered a wrong input!");
                    break;
            }
        }
    }
}