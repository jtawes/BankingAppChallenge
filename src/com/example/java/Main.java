package com.example.java;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
	/*
	Create a simple banking application.
	There should be a Bank class with an ArrayList of Branches.
	Each Branch should have an ArrayList of Customers.
	The Customer class should have an ArrayList of Doubles (transactions).

	Customer:
	    Name, and ArrayList of doubles
    Branch:
        Need to be able to add a new customer and initial transaction amount.
        Also needs to add additional transactions for that customer/branch.
    Bank:
        Add a new branch.
        Add a customer to that branch with initial transaction.
        Add a transaction for an existing customer for that branch.
        Show a list of customers for a particular branch and optionally a list of their transactions.

    Demonstrate autoboxing and unboxing in your code.
        Hint: Transactions - add data validation (e.g. check if exists or does not exist, etc.)
        Think about where you are adding the code to perform certain actions.
	 */
        boolean quit = false;
        openBankingApp();
        printActions();
        while(!quit) {
            System.out.println("\nEnter action: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {
                case 0:
                    System.out.println("\nExiting Banking App...");
                    quit = true;
                    break;
                case 1:
                    printBranches();
                    break;
                case 2:
                    addNewBranch();
                    break;
                case 3:
                    addCustomerToBranch();
                    break;
                case 4:
                    addTransaction();
                    break;
                case 5:
                    listCustomersByBranch();
                    break;
                case 6:
                    listTransactionsByCustomer();
                    break;
                case 7:
                    printActions();
                    break;
            }
        }

    }

    private static void printBranches() {
        System.out.println("List of branches: ");
        for(int i = 0; i < bank.getBranches().size(); i++) {
            System.out.println("Branch " + i + ": " + bank.getBranch(i).getBranchName());
        }
    }

    private static void addNewBranch() {
        System.out.println("Enter branch to be added: ");
        String branchName = scanner.nextLine();
        if(bank.findBranch(branchName) < 0) {
            Branch newBranch = Branch.createBranch(branchName);
            bank.addNewBranch(newBranch);
            System.out.println("Branch " + branchName + " successfully added.");
        } else {
            System.out.println("Error creating branch.");
        }
    }

    private static void addCustomerToBranch() {
        System.out.println("Enter branch: ");
        String branchName = scanner.nextLine();
        if(bank.findBranch(branchName) < 0) {
            System.out.println("Branch does not exist");
            return;
        }
        System.out.println("Made it this far in 'addCustomerToBranch'.");
        int branchPosition = bank.findBranch(branchName);
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.println("Enter first transaction: ");
        double firstTransaction = scanner.nextDouble();

        Branch currentBranch = bank.getBranch(branchPosition);
        System.out.println(currentBranch.getBranchName() + " is the current Branch.");
        int customerPosition = currentBranch.findCustomer(customerName);
        System.out.println("Position of customer should be less than one: " + customerPosition);
        if(customerPosition < 0){
            System.out.println("Made it past if statement");
            Customer newCustomer = Customer.createCustomer(customerName, firstTransaction);
            System.out.println("New customer created: " + newCustomer.getName());
            currentBranch.addNewCustomer(newCustomer);
            int position = currentBranch.findCustomer(customerName);
            System.out.println("New customer " + currentBranch.getCustomer(position).getName() + " added to " + currentBranch.getBranchName() + " with an initial transaction of " + firstTransaction);
            newCustomer.addTransaction(firstTransaction);
        } else {
            System.out.println("Error adding customer to branch.");
        }
    }

    private static void addTransaction() {
        System.out.println("Enter branch: ");
        String branchName = scanner.nextLine();
        if(bank.findBranch(branchName) < 0) {
            System.out.println("Branch does not exist");
            return;
        }
        int branchPosition = bank.findBranch(branchName);
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.println("Enter transaction: ");
        double transaction = scanner.nextDouble();

        Branch currentBranch = bank.getBranch(branchPosition);
        int currentCustomerPosition = currentBranch.findCustomer(customerName);
        if(currentCustomerPosition < 0) {
            System.out.println("Customer not on file at this branch.");
            return;
        } else {
            currentBranch.getCustomer(currentCustomerPosition).addTransaction(transaction);
            System.out.println("Transaction " + transaction + " added to " + currentBranch.getCustomer(currentCustomerPosition).getName() + " with an account at " + currentBranch.getBranchName() );
        }
    }

    private static void listCustomersByBranch() {
        System.out.println("Enter branch: ");
        String branchName = scanner.nextLine();
        if(bank.findBranch(branchName) < 0) {
            System.out.println("Branch does not exist");
            return;
        }
        int branchPosition = bank.findBranch(branchName);
        Branch currentBranch = bank.getBranch(branchPosition);
        for(int i = 0; i < currentBranch.getCustomers().size(); i++) {
            System.out.println("Customer " + i + ": " + currentBranch.getCustomer(i).getName());
        }
    }

    private static void listTransactionsByCustomer() {
        System.out.printf("Enter branch: ");
        String branchName = scanner.nextLine();
        if(bank.findBranch(branchName) < 0) {
            System.out.println("Branch does not exist.");
            return;
        }
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        int branchPosition = bank.findBranch(branchName);
        Branch currentBranch = bank.getBranch(branchPosition);
        int customerPosition = currentBranch.findCustomer(customerName);
        Customer currentCustomer = currentBranch.getCustomer(customerPosition);
        System.out.println("Transactions for " + currentCustomer.getName() + ": ");
        for(int i = 0; i < currentCustomer.getTransactions().size(); i++) {
            System.out.println("Transaction " + i + ": " + currentCustomer.getTransactions().get(i));
        }

    }

    private static void openBankingApp() {
        System.out.println("Banking App starting...");
    }

    public static void printActions() {
        System.out.println("\nAvailable actions: \npress");
        System.out.println("0 - Quit Banking App\n" +
                            "1 - Print a list of branches\n" +
                            "2 - Add a new branch\n" +
                            "3 - Add a new customer to a branch\n" +
                            "4 - Add a transaction for a customer\n" +
                            "5 - Print a list of customers by branch\n" +
                            "6 - Print a list of transactions by customer\n" +
                            "7 - Print a list of available actions");
        System.out.println("Choose your action: ");
    }
}
