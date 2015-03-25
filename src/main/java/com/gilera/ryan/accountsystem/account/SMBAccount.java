package com.gilera.ryan.accountsystem.account;

/**
 * The SMB account class
 * @author Ryan Gilera
 */
public class SMBAccount extends BaseAccount {

    public SMBAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.SMB, 
                AccountType.SMB.getInterest());
    }
}
