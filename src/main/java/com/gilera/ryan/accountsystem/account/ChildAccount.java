package com.gilera.ryan.accountsystem.account;

/**
 * The child account class
 * @author Ryan Gilera
 */
public class ChildAccount extends BaseAccount {

    public ChildAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.CHILD, 
                AccountType.CHILD.getInterest());
    }
    
}
