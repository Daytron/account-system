/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

/**
 *
 * @author Ryan Gilera
 */
public enum AccountType {
    BUSINESS("Business"),
    CURRENT("Current"),
    SAVINGS("Savings");
    
    private final String text;
    
    private AccountType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
    
}
