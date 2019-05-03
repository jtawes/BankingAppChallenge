package com.example.java;

import java.util.ArrayList;

public class Bank {

    private ArrayList<Branch> branches;

    public Bank() {
        this.branches = new ArrayList<Branch>();
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public Branch getBranch(int branchPosition) {
        return branches.get(branchPosition);
    }

    public boolean  addNewBranch(Branch branch) {
        if(findBranch(branch) >= 0) {
            System.out.println("Branch already exists.");
            return false;
        }

        branches.add(branch);
        return true;
    }

    public boolean  addNewBranch(Branch branch, Customer customer, double initialTransaction) {
        if(findBranch(branch) >= 0) {
            System.out.println("Branch already exists.");
            return false;
        }

        if(branch.findCustomer(customer) >= 0) {
            System.out.printf("Customer already exists");
            return false;
        }
        branches.add(branch);
        branch.addNewCustomer(customer);
        customer.addTransaction(initialTransaction);
        System.out.println("Branch " + branch + " created. " + customer + " created and added " + initialTransaction + " as initial transaction.");
        return true;
    }

    public boolean addNewTransaction(Branch branch, Customer customer, double transaction) {
        if((findBranch(branch) < 0) && (branch.findCustomer(customer) < 0)) {
            branch.addTransaction(customer, transaction);
            System.out.println("Branch " + branch.toString() + " customer " + customer.getName() + " added transaction of " + transaction);
            return true;
        }
        System.out.println("Error adding transaction.");
        return false;
    }

    private int findBranch(Branch branch) {
        return this.branches.indexOf(branch);
    }

    public int findBranch(String branchName) {
        for(int i = 0; i < this.branches.size(); i++) {
            Branch branch = this.branches.get(i);
            if(branch.getBranchName().equals(branchName)) {
                return i;
            }
        }
        return -1;
    }
}
