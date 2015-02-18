/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.asset.Money;
import com.gilera.ryan.accountsystem.asset.Sign;
import java.util.ArrayList;
import java.util.Date;
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;
import java.util.List;

/**
 *
 * @author Ryan Gilera
 */
public abstract class BaseAccount implements Account {

    private static final Money OVERDRAFT_PENALTY_FEE
            = new Money(Sign.Positive, 20, 00);

    private Money balance;
    private final List<String> listOfAccountHolders;
    private final List<Transaction> listOfTransactions;
    private final String interestRate;
    private final long accountNumber;
    private final AccountType accountType;
    private final long clientID;

    //Set up a new account 
    public BaseAccount(String accountName, long accountNumber,
            AccountType accountType, long clientID, String interestRate) {
        this.listOfAccountHolders = new ArrayList<>();
        this.listOfTransactions = new ArrayList<>();

        this.balance = new Money();

        // Remove extra spaces in the beginning and at the end
        accountName = accountName.trim();
        // Normalise string name to lower case
        accountName = accountName.toLowerCase();
        // Converts first letter of each word to capital
        // ex. "ryan gilera" to "Ryan Gilera"
        StringBuilder tempNameHolder = new StringBuilder();
        String[] stringHolderArray = accountName.split(" ");
        for (String eachWordBeforeSpace : stringHolderArray) {
            char[] eachWordArray = eachWordBeforeSpace.trim().toCharArray();
            eachWordArray[0] = Character.toUpperCase(eachWordArray[0]);
            eachWordBeforeSpace = new String(eachWordArray);

            tempNameHolder.append(eachWordBeforeSpace).append(" ");
        }

        // Add name to account holders
        this.listOfAccountHolders.add(tempNameHolder.toString());
        
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

    public String getInterestRate() {
        return interestRate;
    }
    
    public String getInterestRateInPercentage() {
        String rate = this.interestRate;
        
        String[] aStringArray = rate.split("\\.");
        
        // Discard first element assuming interest rate should 
        // be less than 100 percent rate
        String newInterestRate = aStringArray[1];
        
        // Discard extra zeros in the first 2 digits
        int count = 0;
        while (true) {
            if (count < 2 && newInterestRate.charAt(0) == '0') {
                newInterestRate = newInterestRate.substring(1);
                count += 1;
            } else {
                break;
            }
        }
        
        // Calculate decimal places to shift to percentage
        int decimalPlaces = 2 - count;
        
        // Prepare the output
        if (decimalPlaces == 0) {
            newInterestRate = "0." + newInterestRate;
        } else {
            newInterestRate = newInterestRate.substring(0, decimalPlaces) 
                    + "." + newInterestRate.substring(decimalPlaces);
        }
        
        return newInterestRate;
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
        // if balance is less than or equal to zero
        // skip interest
        if (this.balance.isLessThanOrEqualTo(new Money())) {
            return;
        }

        Money amountWithInterest = this.balance.multipliedBy(
                this.interestRate);

        this.balance = amountWithInterest.plus(this.balance);

        addTransaction(new Date(), TransactionType.PAID_INTEREST,
                amountWithInterest, this.balance);

        //System.out.println("Acount: " + getAccountNum()
        //    + " Balance: " + getBalance());
    }

    public void applyOverdraftPenaltyIfPossible() {
        if (getBalance().isLessThan(new Money())) {
            //System.out.print("Overdraft detected. " +
            //        "Previous balance " + getBalance() + ".");
            this.balance = this.balance.minus(OVERDRAFT_PENALTY_FEE);
            //System.out.println(" New balance: " + getBalance() + ".");
            addTransaction(new Date(), TransactionType.PAID_OVERDRAFT_PENALTY,
                    OVERDRAFT_PENALTY_FEE, this.balance);
        }
    }

    public void addTransaction(Date date, TransactionType transactionType,
            Money amount, Money balance) {
        listOfTransactions.add(new Transaction(date, transactionType,
                amount, balance));
    }
    
    public boolean isTransactionsEmpty() {
        return this.listOfTransactions.isEmpty();
    }

    public ArrayList<Transaction> getTransactions() {
        return (ArrayList<Transaction>) listOfTransactions;
    }
}
