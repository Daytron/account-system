/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.log;

import java.util.Date;

/**
 *
 * @author Ryan Gilera
 */
public class Transaction {

    Date time;
    String transactionType;
    double amount;

    public Transaction(Date _time, String _transactionType, double _amount) {
        time = _time;
        transactionType = _transactionType;
        amount = _amount;

    }

    public Date getDate() {
        return time;
    }

    public String getType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

}
