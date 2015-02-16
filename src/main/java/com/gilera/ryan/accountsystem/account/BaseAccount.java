/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import java.util.ArrayList;
import java.util.Date;
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;

/**
 *
 * @author Ryan Gilera
 */
public class BaseAccount implements Account {

    public double balance;
    ArrayList<String> listOfAccountHolders = new ArrayList<>();
    ArrayList<Transaction> listOfTransactions = new ArrayList<>();
    public double interestRate;
    public int acc_number;
    public final AccountType accountType;
    int id;

    //Set up a new account 
    public BaseAccount(String accountName, int accountNumber, 
            AccountType accountType, int _id) {
        this.listOfAccountHolders.add(accountName);
        this.acc_number = accountNumber;
        this.accountType = accountType;
    }

    //Add account holder 
    public void AddAccHolder(String accountName, int accountNumber) {
        listOfAccountHolders.add(accountName);
        acc_number = accountNumber;
    }

    public String getHolderName() {
        return listOfAccountHolders.get(0);
    }

    public int getID() {
        return id;
    }

    public int getAccountNum() {
        return acc_number;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void payWithInterest() {
        balance += interestRate * balance;
    }

    public void addTransaction(Date d, TransactionType trans_Type, double amount) {
        listOfTransactions.add(new Transaction(d, trans_Type, amount));
    }

    public ArrayList<Transaction> getTransactions() {
        return listOfTransactions;
    }
}
