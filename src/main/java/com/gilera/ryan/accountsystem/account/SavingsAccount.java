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
public class SavingsAccount extends BaseAccount {

    public SavingsAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.SAVINGS,
                AccountType.SAVINGS.getInterest());
    }
}
