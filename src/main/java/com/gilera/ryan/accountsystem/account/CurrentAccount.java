package com.gilera.ryan.accountsystem.account;

/**
 * The current account class
 * 
 * @author Ryan Gilera
 */
public class CurrentAccount extends BaseAccount {

    public CurrentAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.CURRENT,
                AccountType.CURRENT.getInterest());
    }
}
