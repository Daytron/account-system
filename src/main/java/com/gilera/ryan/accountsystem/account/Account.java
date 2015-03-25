package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.utility.Money;

/**
 * An interface as the blueprint for all accounts.
 * @author Ryan Gilera
 */
public interface Account {
    public void deposit(Money amount); 
    public Money getBalance();
}
