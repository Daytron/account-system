package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.utility.Money;
import com.gilera.ryan.accountsystem.utility.Sign;

/**
 * An enumeration type for holding all constant values
 * for all types of accounts.
 * 
 * @author Ryan Gilera
 */
public enum AccountType {
    BUSINESS("Business",
            "0.0256", 
            new Money(Sign.Positive, 500, 00), 
            new Money(Sign.Negative, 200, 00)),
    CURRENT("Current",
            "0.025", 
            new Money(Sign.Positive, 500, 00), 
            new Money(Sign.Negative, 200, 00)),
    SAVINGS("Savings", 
            "0.0345", 
            new Money(Sign.Positive, 300, 00), 
            new Money(Sign.Negative, 200, 00)),
    IRA("IRA", 
            "0.082", 
            new Money(Sign.Positive, 100, 00),
            new Money(Sign.Negative, 200, 00)),
    SMB("SMB", 
            "0.0234", 
            new Money(Sign.Positive, 100, 00), 
            new Money(Sign.Negative, 400, 00)),
    STUDENT("Student", 
            "0.012", 
            new Money(Sign.Positive, 200, 00), 
            new Money()),
    CASH_INVESTMENT("Cash investment", 
            "0.0123",
            new Money(Sign.Positive, 200, 00), 
            new Money(Sign.Negative, 200, 00)),
    CHILD("Child", 
            "0.0123",
            new Money(Sign.Positive, 200, 00), 
            new Money(Sign.Negative, 500, 00)),
    INTERNATIONAL("International", 
            "0.0123",
            new Money(Sign.Positive, 200, 00), 
            new Money(Sign.Negative, 200, 00));
    
    private final String text;
    private final String interest;
    private final Money maxWithdrawal;
    private final Money overdraftLimit;
    
    private AccountType(String text, String interest, 
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
    
    public String getInterest() {
        return this.interest;
    }

    public Money getOverdraftLimit() {
        return overdraftLimit;
    }
    
    
}
