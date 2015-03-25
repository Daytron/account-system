package com.gilera.ryan.accountsystem.account;

/**
 * The IRA account class
 * 
 * @author Ryan Gilera
 */
public class IRAccount extends BaseAccount {

    public IRAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.IRA,
                AccountType.IRA.getInterest());
    }
}
