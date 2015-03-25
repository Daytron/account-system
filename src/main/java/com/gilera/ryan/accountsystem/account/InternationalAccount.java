package com.gilera.ryan.accountsystem.account;

/**
 * The international account class
 * @author Ryan Gilera
 */
public class InternationalAccount extends BaseAccount {

    public InternationalAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.INTERNATIONAL, 
                AccountType.INTERNATIONAL.getInterest());
    }
    
}
