/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.log;

import com.gilera.ryan.accountsystem.asset.Money;
import java.util.Date;

/**
 *
 * @author Ryan Gilera
 */
public class Transaction {

    private final Date dateOfTransaction;
    private final TransactionType transactionType;
    private final Money amount;

    public Transaction(Date logDate, TransactionType 
            transactionType, Money amountToLog) {
        this.dateOfTransaction = logDate;
        this.transactionType = transactionType;
        this.amount = amountToLog;

    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getAmountInString() {
        return amount.toString();
    }

}
