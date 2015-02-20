package account;

import java.util.*;

/**
 * 
 */
public abstract class BaseAccount implements Account {

    /**
     * 
     */
    public BaseAccount() {
    }

    /**
     * 
     */
    private static Money OVERDRAFT_PENALTY_FEE = 20;

    /**
     * 
     */
    private Money balance;

    /**
     * 
     */
    private List<Transaction> listOfTransactions;

    /**
     * 
     */
    private String interestRate;

    /**
     * 
     */
    private String accountNumber;

    /**
     * 
     */
    private AccountType accountType;

    /**
     * 
     */
    private Client client;


    /**
     * 
     */
    public Client has;




    /**
     * @return
     */
    public String getClientName() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public UUID getClientID() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Client getClient() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getAccountNum() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public String getInterestRate() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public String getInterestRateInPercentage() {
        // TODO implement here
        return "";
    }

    /**
     * @param amountToWithdraw 
     * @return
     */
    public void withdraw(Money amountToWithdraw) {
        // TODO implement here
        return null;
    }

    /**
     * @param amountToDeposit 
     * @return
     */
    public void deposit(Money amountToDeposit) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Money getBalance() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public AccountType getAccountType() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void payWithInterest() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void applyOverdraftPenaltyIfPossible() {
        // TODO implement here
        return null;
    }

    /**
     * @param date 
     * @param transactionType 
     * @param amount Money 
     * @param balance Money 
     * @return
     */
    public void addTransaction(Date date, TransactionType transactionType, void amount Money, void balance Money) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean isTransactionsEmpty() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public List<Transaction> getTransactions() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String toString() {
        // TODO implement here
        return "";
    }

}