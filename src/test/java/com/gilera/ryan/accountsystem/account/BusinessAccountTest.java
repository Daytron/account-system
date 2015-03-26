/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.log.TransactionType;
import com.gilera.ryan.accountsystem.utility.Money;
import com.gilera.ryan.accountsystem.utility.Sign;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Ryan Gilera
 */
public class BusinessAccountTest {
    private Client clientA;
    private Client clientB;
    private Client clientC;
    
    
    public BusinessAccountTest() {
        clientA = new Client("Ryan Gilera");
        clientB = new Client("Emma Swan");
        clientC = new Client("Mary Margaret");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddAccountHolder() {
        // Case 1 same client
        // Given
        BusinessAccount bAccountCase1 = new BusinessAccount(clientA, "1000001");
        int expectedCase1 = 2;
        
        // When
        int resultCase1 = bAccountCase1.addAccountHolder(clientA);
        
        // Then
        assertEquals(expectedCase1, resultCase1);
        
        // Case 2 first time to add client
        // Given
        BusinessAccount bAccountCase2 = new BusinessAccount(clientA, "1000002");
        int expectedCase2 = 0;
        
        // When
        int resultCase2 = bAccountCase2.addAccountHolder(clientB);
        
        // Then
        assertEquals(expectedCase2, resultCase2);
        
        // Case 3 list of clients is full
        // Given
        BusinessAccount bAccountCase3 = new BusinessAccount(clientA, "1000003");
        int expectedCase3 = 1;
        
        // When
        bAccountCase3.addAccountHolder(clientB);
        int resultCase3 = bAccountCase3.addAccountHolder(clientC);
        
        // Then
        assertEquals(expectedCase3, resultCase3);
    }
    
    @Test
    public void testGetListOfAccountHolders() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        boolean expResult = false;
        
        // When
        List<Client> aListOfClients = bAccount.getListOfAccountHolders();
        boolean isNotEmptyList = aListOfClients.isEmpty();
        
        // Then
        assertEquals(expResult, isNotEmptyList);
        
    }
    
    @Test
    public void testGetAccountNumber() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        String expString = "1000001";
        
        // When
        String result = bAccount.getAccountNum();
        
        // Then
        assertEquals(expString, result);
    }
    
    @Test
    public void testGetInterestRate() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        String expInterest = "0.0256";
        
        // When
        String result = bAccount.getInterestRate();
        
        // Then
        assertEquals(expInterest, result);
    }
    
    @Test
    public void testGetInterestRateInPercentage() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        String expInterest = "2.56";
        
        // When
        String result = bAccount.getInterestRateInPercentage();
        
        // Then
        assertEquals(expInterest, result);
    }
    
    @Test
    public void testDepositWithdrawBalance() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        Money aMoney = new Money(25000);
        Money expMoney = new Money();
        bAccount.deposit(aMoney);
        
        // When
        bAccount.withdraw(aMoney);
        Money result = bAccount.getBalance();
        
        // Then
        assertEquals(result, expMoney);
    }
    
    @Test
    public void testGetAccountType() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        AccountType expAccountType = AccountType.BUSINESS;
        
        // When
        AccountType result = bAccount.getAccountType();
        
        // Then
        assertEquals(result, expAccountType);
    }
    
    @Test
    public void testPayInterest() {
        // Case 1: Balance > 0
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        bAccount.deposit(new Money(1000));
        Money expMoney = new Money(Sign.Positive, 1025, 60);
        
        // When
        bAccount.payWithInterest();
        Money result = bAccount.getBalance();
        
        // Then
        assertEquals(result, expMoney);
        
        // Case 1: Balance < 0
        // Given
        BusinessAccount bAccount2 = new BusinessAccount(clientA, "1000001");
        bAccount2.withdraw(new Money(200));
        Money expMoney2 = new Money(Sign.Negative,200);
        
        // When
        bAccount2.payWithInterest();
        Money result2 = bAccount2.getBalance();
        
        // Then
        assertEquals(result2, expMoney2);
    }
    
    @Test
    @Ignore
    public void testApplyOverdraftPenaltyIfPossible() {
        // Case 1: Balance > 0
        // Given
        BusinessAccount bAccount1 = new BusinessAccount(clientA, "1000001");
        bAccount1.deposit(new Money(200));
        Money expMoney1 = new Money(Sign.Positive, 200, 0);
        
        // When
        bAccount1.applyOverdraftPenaltyIfPossible();
        Money result1 = bAccount1.getBalance();
        
        // Then
        assertEquals(result1, expMoney1);

        // Case 2: Balance < 0
        // Given
        BusinessAccount bAccount2 = new BusinessAccount(clientA, "1000001");
        bAccount2.withdraw(new Money(200));
        Money expMoney2 = new Money(Sign.Negative, 220, 0);
        
        // When
        bAccount2.applyOverdraftPenaltyIfPossible();
        Money result2 = bAccount2.getBalance();
        
        // Then
        assertEquals(result2, expMoney2);
        
        // Case 3: Balance == 0
        // Given
        BusinessAccount bAccount3 = new BusinessAccount(clientA, "1000001");
        Money expMoney3 = new Money();
        
        // When
        bAccount3.applyOverdraftPenaltyIfPossible();
        Money result3 = bAccount3.getBalance();
        
        // Then
        assertEquals(result3, expMoney3);
    }
    
    @Test
    public void testIsTransactionsEmptyAndAddTransaction() {
        // Case 1: Empty transactions
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        boolean expResult = true;
        
        // When
        boolean result = bAccount.isTransactionsEmpty();
        
        // Then
        assertEquals(result, expResult);
        
        // Case 2: With transactions
        // Given
        BusinessAccount bAccount2 = new BusinessAccount(clientA, "1000001");
        bAccount2.deposit(new Money(100));
        Date today = new Date();
        boolean expResult2 = false;
        
        // When
        bAccount2.addTransaction(today, TransactionType.DEPOSIT, new Money(100), 
                new Money(100));
        boolean result2 = bAccount2.isTransactionsEmpty();
        
        // Then
        assertEquals(result2, expResult2);
    }
    
    @Test
    public void testGetTransactions() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        bAccount.deposit(new Money(100));
        Date today = new Date();
        boolean expResult = false;
        
        // When
        bAccount.addTransaction(today, TransactionType.DEPOSIT, new Money(100), 
                new Money(100));
        boolean result2 = bAccount.getTransactions().isEmpty();
        
        // Then
        assertEquals(result2, expResult);
    }
    
    @Test
    public void testToString() {
        // Given
        BusinessAccount bAccount = new BusinessAccount(clientA, "1000001");
        
        // When
        String result = bAccount.toString();
        
        // Then
        assertNotNull(result);
    }
}
