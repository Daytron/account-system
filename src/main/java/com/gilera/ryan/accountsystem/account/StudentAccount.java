package com.gilera.ryan.accountsystem.account;

/**
 * The student account class
 * 
 * @author Ryan Gilera
 */
public class StudentAccount extends BaseAccount {

    public StudentAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.STUDENT,
                AccountType.STUDENT.getInterest());
    }
}
