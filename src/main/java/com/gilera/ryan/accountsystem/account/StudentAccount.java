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
public class StudentAccount extends BaseAccount {

    public StudentAccount(String accountOwner, long acc_num, long accountID) {
        super(accountOwner, acc_num, AccountType.STUDENT, accountID, 
                AccountType.STUDENT.getInterest());
    }
}
