package com.example.java;

import java.util.ArrayList;

public class Customer {
    private String name;
    private Double transaction;
    private static ArrayList<Double> transactions;

    public Customer(String name, double transaction) {
        this.name = name;
        this.transaction = transaction;
        this.transactions = new ArrayList<Double>();
    }


    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double transaction) {
        transactions.add(transaction);
    }


    public static Customer createCustomer(String name, double initialTransaction) {
        return new Customer(name, initialTransaction);
    }
}
