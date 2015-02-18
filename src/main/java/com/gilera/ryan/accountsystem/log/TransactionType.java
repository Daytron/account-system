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

    DEPOSIT("Deposit", "\u001B[34m"),
    VIEW_BALANCE("View Balance", "\u001B[36m"),
    WITHDRAW("Withdraw", "\u001B[35m"),
    TRANSFER("Funds Transfer", "\u001B[33m"),
    PAID_INTEREST("Paid Interest", "\u001B[32m"),
    PAID_OVERDRAFT_PENALTY("Paid Overdraft Penalty", "\u001B[31m");

    private final String text;
    private final String colour;

    private TransactionType(String text, String colour) {
        this.text = text;
        this.colour = colour;
    }

    public String getText() {
        return text;
    }

    public String getColour() {
        return colour;
    }
    
    
}
