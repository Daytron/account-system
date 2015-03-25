package com.gilera.ryan.accountsystem.account;

/**
 * The business account class
 * @author Ryan Gilera
 */
public class BusinessAccount extends BaseAccount {

    public BusinessAccount(Client client, String acc_num) {
        super(client, acc_num, AccountType.BUSINESS,  
                AccountType.BUSINESS.getInterest());
    }
}
