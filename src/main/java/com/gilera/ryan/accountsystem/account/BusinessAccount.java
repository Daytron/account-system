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
public class BusinessAccount extends BaseAccount {

    public BusinessAccount(String accountOwner, long acc_num, long accountID) {
        super(accountOwner, acc_num, AccountType.BUSINESS, accountID, 
                AccountType.BUSINESS.getInterest());
    }
}
