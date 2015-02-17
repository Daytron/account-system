/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.asset.Money;
import com.gilera.ryan.accountsystem.asset.Sign;

/**
 *
 * @author Ryan Gilera
 */
public enum AccountType {
    BUSINESS("Business",0.0256, 
            new Money(Sign.Positive, 500, 00), 
            new Money(Sign.Negative, 200, 00)),
    CURRENT("Current",0.025, 
            new Money(Sign.Positive, 500, 00), 
            new Money(Sign.Negative, 200, 00)),
    SAVINGS("Savings", 0.0345, 
            new Money(Sign.Positive, 300, 00), 
            new Money(Sign.Negative, 200, 00)),
    IRA("IRA", 0.082, 
            new Money(Sign.Positive, 100, 00),
            new Money(Sign.Negative, 200, 00)),
    SMB("SMB", 0.0234, 
            new Money(Sign.Positive, 100, 00), 
            new Money(Sign.Negative, 200, 00)),
    STUDENT("Student", 0.012, 
            new Money(Sign.Positive, 200, 00), 
            new Money(Sign.Negative, 200, 00)),
    CASH_INVESTMENT("Cash investment", 0.0123,
            new Money(Sign.Positive, 200, 00), 
            new Money(Sign.Negative, 200, 00)),
    CHILD("Child", 0.0123,
            new Money(Sign.Positive, 200, 00), 
            new Money(Sign.Negative, 200, 00)),
    INTERNATIONAL("International", 0.0123,
            new Money(Sign.Positive, 200, 00), 
            new Money(Sign.Negative, 200, 00));
    
    private final String text;
    private final double interest;
    private final Money maxWithdrawal;
    private final Money overdraftLimit;
    
    private AccountType(String text, double interest, 
            Money maxAmount, Money overdraftAmount) {
        this.text = text;
        this.interest = interest;
        this.maxWithdrawal = maxAmount;
        this.overdraftLimit = overdraftAmount;
    }

    public String getText() {
        return text;
    }
    
    public Money getMaxWithdrawal() {
        return this.maxWithdrawal;
    }
    
    public String getMaxWithdrawalStr() {
        return this.maxWithdrawal.toString();
    }
    
    public double getInterest() {
        return this.interest;
    }
    
    public String getInterestString() {
        return Double.toString(this.interest);
    }

    public Money getOverdraftLimit() {
        return overdraftLimit;
    }
    
    
}
