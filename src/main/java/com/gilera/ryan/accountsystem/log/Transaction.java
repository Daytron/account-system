/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.log;

import com.gilera.ryan.accountsystem.utility.Money;
import java.util.Date;

/**
 *
 * @author Ryan Gilera
 */
public class Transaction {

    private final Date dateOfTransaction;
    private final TransactionType transactionType;
    private final Money amount;
    private final Money balance;

    public Transaction(Date logDate, TransactionType transactionType, Money amountToLog, Money balance) {
        this.dateOfTransaction = logDate;
        this.transactionType = transactionType;
        this.amount = amountToLog;
        this.balance = balance;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public Money getAmount() {
        return amount;
    }

    public Money getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        final String resetColour = "\u001B[0m";

        String transactionTypeWithColour
                = this.transactionType.getColour()
                + "[" + this.transactionType.getText() + "]";

        return this.dateOfTransaction
                + "   "
                + transactionTypeWithColour
                + "   Amount: "
                + this.amount
                + "   Remaining balance: "
                + this.balance
                + resetColour;
    }

}
