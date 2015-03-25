package com.gilera.ryan.accountsystem.account;

/**
 * The cash investment account class
 * 
 * @author Ryan Gilera
 */
public class CashInvestmentAccount extends BaseAccount {

    public CashInvestmentAccount(Client client, String accountNumber) {
        super(client, accountNumber, AccountType.CASH_INVESTMENT, 
                AccountType.CASH_INVESTMENT.getInterest());
    }
    
}
