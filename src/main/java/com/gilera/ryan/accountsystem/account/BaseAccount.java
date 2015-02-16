/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import java.util.ArrayList;
import java.util.Date;
import com.gilera.ryan.accountsystem.log.Transaction;

/**
 *
 * @author Ryan Gilera
 */
public class BaseAccount implements Account {

    public double balance;
    ArrayList<String> holders = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    public double interestRate;
    public int acc_number;
    public String account_type;
    int id;

    //Set up a new account 
    public BaseAccount(String acc_owner, int acc_num, String acc_type, int _id) {
        holders.add(acc_owner);
        acc_number = acc_num;
        account_type = acc_type;
    }

    //Add account holder 
    public void AddAccHolder(String acc_owner, int acc_num) {
        holders.add(acc_owner);
        acc_number = acc_num;
    }

    public String getHolderName() {
        return holders.get(0);
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
    public double get_balance() {
        return balance;
    }

    public String get_acc_type() {
        return account_type;
    }

    public void interest_rate() {
        balance += interestRate * balance;
    }

    public void addTransaction(Date d, String trans_Type, double amount) {
        transactions.add(new Transaction(d, trans_Type, amount));
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
