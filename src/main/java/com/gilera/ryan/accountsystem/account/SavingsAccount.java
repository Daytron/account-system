package com.gilera.ryan.accountsystem.account;

/**
 * The savings account class
 * 
 * @author Ryan Gilera
 */
public class SavingsAccount extends BaseAccount {

    public SavingsAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.SAVINGS,
                AccountType.SAVINGS.getInterest());
    }
}
