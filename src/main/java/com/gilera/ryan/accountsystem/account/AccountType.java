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
    BUSINESS("Business",0.0256, 500),
    CURRENT("Current",0.025, 500),
    SAVINGS("Savings", 0.0345, 300),
    IRA("IRA", 0.082, 100),
    SMB("SMB", 0.0234, 100),
    STUDENT("Student",0.012, 200);
    
    private final String text;
    private final double interest;
    private final int maxWithdrawal;
    
    private AccountType(String text, double interest, int maxAmount) {
        this.text = text;
        this.interest = interest;
        this.maxWithdrawal = maxAmount;
    }

    public String getText() {
        return text;
    }
    
    public int getMaxWithdrawal() {
        return this.maxWithdrawal;
    }
    
    public String getMaxWithdrawalStr() {
        return Integer.toString(this.maxWithdrawal);
    }
    
    public double getInterest() {
        return this.interest;
    }
    
    public String getInterestString() {
        return Double.toString(this.interest);
    }
    
    
}
