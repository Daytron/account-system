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
public class CashInvestmentAccount extends BaseAccount {

    public CashInvestmentAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.CASH_INVESTMENT, 
                AccountType.CASH_INVESTMENT.getInterest());
    }
    
}
