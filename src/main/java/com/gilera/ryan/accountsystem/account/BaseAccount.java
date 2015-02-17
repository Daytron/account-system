/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.asset.Money;
import java.util.ArrayList;
import java.util.Date;
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Ryan Gilera
 */
public class BaseAccount implements Account {

    private Money balance;
    private final List<String> listOfAccountHolders;
    private final List<Transaction> listOfTransactions;
    private final double interestRate;
    private final long accountNumber;
    private final AccountType accountType;
    private final long clientID;

    //Set up a new account 
    public BaseAccount(String accountName, long accountNumber, 
            AccountType accountType, long clientID, double interestRate) {
        this.listOfAccountHolders = new ArrayList<>();
        this.listOfTransactions = new ArrayList<>();
        
        this.balance = new Money();
        
        this.listOfAccountHolders.add(accountName);
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.clientID = clientID;
        
        this.interestRate = interestRate;
    }

    //Add account holder 
    /*
    public void AddAccHolder(String accountName, long accountNumber) {
        this.listOfAccountHolders.add(accountName);
        this.accountNumber = accountNumber;
    }
  */
    public String getHolderName() {
        return listOfAccountHolders.get(0);
    }

    public long getCustomerID() {
        return clientID;
    }

    public long getAccountNum() {
        return accountNumber;
    }

    public double getInterestRate() {
        return interestRate * 100;
    }

    public void withdraw(Money amountToWithdraw) {
        this.balance = this.balance.minus(amountToWithdraw);
    }

    @Override
    public void deposit(Money amountToDeposit) {
        this.balance = this.balance.plus(amountToDeposit);
    }

    @Override
    public Money getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void payWithInterest() {
        balance = (this.balance.multipliedBy(
                Double.toString(getAccountType().getInterest())))
                .plus(this.balance);
    }

    public void addTransaction(Date date, TransactionType transactionType, 
            Money amount) {
        listOfTransactions.add(new Transaction(date, transactionType, amount));
    }
    
    public boolean isTransactionsEmpty() {
        return this.listOfTransactions.isEmpty();
    }

    public ArrayList<Transaction> getTransactions() {
        return (ArrayList<Transaction>)listOfTransactions;
    }
}
