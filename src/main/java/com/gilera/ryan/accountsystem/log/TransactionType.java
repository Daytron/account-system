/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.log;

/**
 *
 * @author Ryan Gilera
 */
public enum TransactionType {

    DEPOSIT("Deposit"),
    VIEW_BALANCE("View Balance"),
    WITHDRAW("Withdraw"),
    TRANSFER("Transfer");

    private final String text;

    private TransactionType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
