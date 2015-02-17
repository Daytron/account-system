/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

/**
 *
 * @author Ryan Gilera
 */
public class InternationalAccount extends BaseAccount {

    public InternationalAccount(String accountName, long accountNumber, 
            long clientID) {
        super(accountName, accountNumber, AccountType.INTERNATIONAL, clientID, 
                AccountType.INTERNATIONAL.getInterest());
    }
    
}
