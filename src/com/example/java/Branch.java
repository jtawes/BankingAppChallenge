package com.example.java;

import java.util.ArrayList;

public class Branch {
    private String branchName;
    private ArrayList<Customer> customers;

    public Branch(String branchName) {
        this.branchName = branchName;
        this.customers = new ArrayList<Customer>();
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(int customerPosition) {
        return customers.get(customerPosition);
    }

    public boolean  addNewCustomer(Customer customer) {
        if(findCustomer(customer) >= 0) {
            System.out.println("Customer already exists.");
            return false;
        }

        customers.add(customer);
        return true;
    }

    public boolean addTransaction(Customer customer, double transaction) {
        if(findCustomer(customer.getName()) >= 0) {
            int position = findCustomer(customer);
            customer.addTransaction(transaction);
            System.out.println("Transaction " + transaction + " successfully added.");
            return true;
        }

        System.out.println("Customer " + customer.getName() + " not found.");
        return false;
    }


    public int findCustomer(Customer customer) {
        return this.customers.indexOf(customer);
    }

    public int findCustomer(String customerName) {
        for(int i = 0; i < this.customers.size(); i++) {
            Customer customers = this.customers.get(i);
            if(customers.getName().equals(customerName)) {
                return i;
            }
        }
        return -1;
    }

    public static Branch createBranch(String branchName) {
        return new Branch(branchName);
    }
}
