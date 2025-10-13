package com.pluralsight;

public class HomeScreen {

    public static void showHomeScreen(){
        final String BOLD = "\u001B[1m";
        final String RESET = "\u001B[0m";

        System.out.println("Please select one of the following options:");
        System.out.println();
        System.out.println(BOLD + "D)" + RESET + " Add Deposit");
        System.out.println(BOLD + "P)" + RESET + " Make Payment (Debit)");
        System.out.println(BOLD + "L)" + RESET + " View Ledger");
        System.out.println(BOLD + "X)" + RESET + " Exit");
        System.out.print("Enter your option: ");
    }

    public static void welcomeMessage(){
        System.out.println();
        System.out.println("==========================================");
        System.out.println("        Welcome to LedgerLink!");
        System.out.println("==========================================");
    }

    public static void ledgerMenu(){
        final String BOLD = "\u001B[1m";
        final String RESET = "\u001B[0m";

        System.out.println("Please select one of the following options:");
        System.out.println();
        System.out.println(BOLD + "A)" + RESET + " All");
        System.out.println(BOLD + "D)" + RESET + " Deposits");
        System.out.println(BOLD + "P)" + RESET + " Payments");
        System.out.println(BOLD + "R)" + RESET + " Report");
        System.out.println(BOLD + "H)" + RESET + " Home");
        System.out.print("Enter your option: ");
    }

    public static void reportsMenu(){
        final String BOLD = "\u001B[1m";
        final String RESET = "\u001B[0m";

        System.out.println("Please select one of the following options:");
        System.out.println();
        System.out.println(BOLD + "1)" + RESET + " Month To Date");
        System.out.println(BOLD + "2)" + RESET + " Previous Month");
        System.out.println(BOLD + "3)" + RESET + " Year To Date");
        System.out.println(BOLD + "4)" + RESET + " Previous Year");
        System.out.println(BOLD + "5)" + RESET + " Search by Vendor");
        System.out.println(BOLD + "0)" + RESET + " Back");
        System.out.print("Enter your option: ");
    }
}
