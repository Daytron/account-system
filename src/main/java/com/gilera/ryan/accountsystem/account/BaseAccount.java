/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.utility.Money;
import com.gilera.ryan.accountsystem.utility.Sign;
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
    private final List<Client> listOfAccountHolders;
    private final List<Transaction> listOfTransactions;
    private final String interestRate;
    private final String accountNumber;
    private final AccountType accountType;
    private int clientCounter;

    //Set up a new account 
    public BaseAccount(Client owner, String accountNumber,
            AccountType accountType, String interestRate) {
        this.listOfAccountHolders = new ArrayList<>();
        this.listOfTransactions = new ArrayList<>();
        this.listOfAccountHolders.add(owner);
        this.clientCounter = 1;

        // Initial balance at zero
        // See Money class for other constructors
        this.balance = new Money();

        this.accountNumber = accountNumber;
        this.accountType = accountType;

        this.interestRate = interestRate;
    }

    //Add account holder 
    public int addAccountHolder(Client client) {
        // makes sure you only add once for joint account
        // also makes sure you don't add the same person again
        if (clientCounter < 2) {
            if (client.getId() != this.listOfAccountHolders.get(0).getId()) {
                this.listOfAccountHolders.add(client);
                this.clientCounter += 1;
                return 0;
            } else {
                return 2;
            }

        } else {
            return 1;
        }
    }

    public List<Client> getListOfAccountHolders() {
        return listOfAccountHolders;
    }

    public String getAccountNum() {
        return this.accountNumber;
    }

    public String getInterestRate() {
        return this.interestRate;
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
        return this.balance;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void payWithInterest() {
        // if balance is less than or equal to zero
        // skip interest
        if (this.balance.isLessThanOrEqualTo(new Money())) {
            return;
        }

        Money amountWithInterest;

        // If money is too small to calculate interest amount
        // set amountWithInterest to zero
        // Ex. if balance is £ 0.01
        try {
            amountWithInterest = this.balance.multipliedBy(
                    this.interestRate);
        } catch (Exception e) {
            // Empty money
            amountWithInterest = new Money();
        }

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
        this.listOfTransactions.add(new Transaction(date, transactionType,
                amount, balance));
    }

    public boolean isTransactionsEmpty() {
        return this.listOfTransactions.isEmpty();
    }

    public List<Transaction> getTransactions() {
        return this.listOfTransactions;
    }

    @Override
    public String toString() {
        String client_s = "";

        for (Client client : this.listOfAccountHolders) {
            client_s += "\n";
            client_s += client.toString();
        }

        return "Account summary:\n"
                + "\nAccount Number: "
                + getAccountNum()
                + client_s
                + "\nBalance: "
                + getBalance().toString()
                + "\n\nAccount Type: "
                + getAccountType().getText()
                + "\nInterest rate: "
                + getInterestRateInPercentage() + "%"
                + "\nMax Daily Withdrawal: "
                + getAccountType().getMaxWithdrawalStr()
                + "\nOverdraft Limit: "
                + getAccountType().getOverdraftLimit();
    }

}
