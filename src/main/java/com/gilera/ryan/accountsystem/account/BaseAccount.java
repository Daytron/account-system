package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.utility.Money;
import com.gilera.ryan.accountsystem.utility.Sign;
import java.util.ArrayList;
import java.util.Date;
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;
import java.util.List;

/**
 * An abstract class that realise the Account interface. 
 * It's the base class for all account functionalities.
 * 
 * @author Ryan Gilera
 */
public abstract class BaseAccount implements Account {

    private static final Money OVERDRAFT_PENALTY_FEE
            = new Money(Sign.Positive, 20, 00);

    private Money balance;
    private final List<Client> listOfAccountHolders;
    private final List<Transaction> listOfTransactions;
    private final String interestRate;
    private final String accountNumber;
    private final AccountType accountType;
    private int clientCounter;

    // Constructor for new objects
    protected BaseAccount(Client owner, String accountNumber,
            AccountType accountType, String interestRate) {
        this.listOfAccountHolders = new ArrayList<>();
        this.listOfTransactions = new ArrayList<>();
        this.listOfAccountHolders.add(owner);
        this.clientCounter = 1;

        // Initial balance at zero
        // See Money class for overloaded constructors
        this.balance = new Money();

        this.accountNumber = accountNumber;
        this.accountType = accountType;

        this.interestRate = interestRate;
    }

    /**
     * Add a Client object to an account. Only allows add another 
     * account holder once as a joint account. It returns an integer
     * value as result of the process. 0 means success that a client 
     * object is added to the account, 1 means failure if there are 
     * already two people in it and 2, another failure code, if
     * the client object argument already exist in the account.
     * 
     * @param client The Client object that represents an account holder
     * @return An integer value that represents output outcome.
     */
    public int addAccountHolder(Client client) {
        // makes sure you only add once for joint account
        // also makes sure you don't add the same person again
        if (clientCounter < 2) {
            if (client.getId() != this.listOfAccountHolders.get(0).getId()) {
                this.listOfAccountHolders.add(client);
                this.clientCounter += 1;
                return 0;
            } else {
                return 2;
            }

        } else {
            return 1;
        }
    }

    /**
     * Returns list of account holders as List object
     * 
     * @return List of account holders as List object
     */
    public List<Client> getListOfAccountHolders() {
        return listOfAccountHolders;
    }

    /**
     * Returns the account number as String.
     * 
     * @return The account number as String
     */
    public String getAccountNum() {
        return this.accountNumber;
    }

    /**
     * Returns the interest rate as String.
     * 
     * @return The interest rate as String.
     */
    public String getInterestRate() {
        return this.interestRate;
    }

    /**
     * Converts the decimal value of the interest in String to 
     * percentage equivalent, still in String format. This for 
     * output display purposes. 
     * 
     * @return The formatted String object 
     */
    public String getInterestRateInPercentage() {
        String rate = this.interestRate;

        String[] aStringArray = rate.split("\\.");

        // Discard first element assuming interest rate should 
        // be less than 100 percent rate
        String newInterestRate = aStringArray[1];

        // Discard extra zeros in the first 2 digits
        int count = 0;
        while (true) {
            if (count < 2 && newInterestRate.charAt(0) == '0') {
                newInterestRate = newInterestRate.substring(1);
                count += 1;
            } else {
                break;
            }
        }

        // Calculate decimal places to shift to percentage
        int decimalPlaces = 2 - count;

        // Prepare the output
        if (decimalPlaces == 0) {
            newInterestRate = "0." + newInterestRate;
        } else {
            newInterestRate = newInterestRate.substring(0, decimalPlaces)
                    + "." + newInterestRate.substring(decimalPlaces);
        }

        return newInterestRate;
    }

    /**
     * Withdraws Money object from balance.
     * 
     * @param amountToWithdraw A Money object to withdraw 
     */
    public void withdraw(Money amountToWithdraw) {
        this.balance = this.balance.minus(amountToWithdraw);
    }

    /**
     * Deposits Money object to the balance. The actual 
     * implementation from the Account interface.
     * 
     * @param amountToDeposit A Money object to deposit 
     */
    @Override
    public void deposit(Money amountToDeposit) {
        this.balance = this.balance.plus(amountToDeposit);
    }

    /**
     * Return the balance as Money object.
     * 
     * @return The balance as Money object
     */
    @Override
    public Money getBalance() {
        return this.balance;
    }

    /**
     * Returns the account type as AccountType object.
     * @return The account type as AccountType object
     */
    public AccountType getAccountType() {
        return this.accountType;
    }

    /**
     * Try and add interest to the account if the balance is
     * greater than zero.
     */
    public void payWithInterest() {
        // if balance is less than or equal to zero
        // skip interest
        if (this.balance.isLessThanOrEqualTo(new Money())) {
            return;
        }

        Money amountWithInterest;

        // If money is too small to calculate interest amount
        // set amountWithInterest to zero
        // Ex. if balance is £ 0.01
        try {
            amountWithInterest = this.balance.multipliedBy(
                    this.interestRate);
        } catch (Exception e) {
            // Empty money
            amountWithInterest = new Money();
        }

        this.balance = amountWithInterest.plus(this.balance);

        addTransaction(new Date(), TransactionType.PAID_INTEREST,
                amountWithInterest, this.balance);
    }

    /**
     * Tries to apply overdraft penalty fee if the account balance 
     * has reach or less than the overdraft limit.
     */
    public void applyOverdraftPenaltyIfPossible() {
        // If the current balance <= to the overdraft limit
        // with an exception if the overdraft limit is zero do not
        // penalize
        if (getBalance().isLessThanOrEqualTo(getAccountType().getOverdraftLimit()) && 
                !getBalance().isZero()) {
            this.balance = this.balance.minus(OVERDRAFT_PENALTY_FEE);
            
            addTransaction(new Date(), TransactionType.PAID_OVERDRAFT_PENALTY,
                    OVERDRAFT_PENALTY_FEE, this.balance);
        }
    }

    /**
     * Creates a new Transaction object.
     * 
     * @param date Date object when the transaction occured
     * @param transactionType TransactionType object
     * @param amount Money object as the amount involve
     * @param balance Money object as the resulting balance
     */
    public void addTransaction(Date date, TransactionType transactionType,
            Money amount, Money balance) {
        this.listOfTransactions.add(new Transaction(date, transactionType,
                amount, balance));
    }

    /**
     * Checks if the list of transactions is empty.
     * 
     * @return True if it is empty otherwise false.
     */
    public boolean isTransactionsEmpty() {
        return this.listOfTransactions.isEmpty();
    }

    /**
     * Returns the list of transactions as List object.
     * 
     * @return The list of transactions as List object
     */
    public List<Transaction> getTransactions() {
        return this.listOfTransactions;
    }

    /**
     * The String equivalent of this object including all
     * Client objects involve.
     * 
     * @return The formatted String object
     */
    @Override
    public String toString() {
        String client_s = "";

        for (Client client : this.listOfAccountHolders) {
            client_s += "\n";
            client_s += client.toString();
        }

        return "Account summary:\n"
                + "\nAccount Number: "
                + getAccountNum()
                + client_s
                + "\nBalance: "
                + getBalance().toString()
                + "\n\nAccount Type: "
                + getAccountType().getText()
                + "\nInterest rate: "
                + getInterestRateInPercentage() + "%"
                + "\nMax Daily Withdrawal: "
                + getAccountType().getMaxWithdrawalStr()
                + "\nOverdraft Limit: "
                + getAccountType().getOverdraftLimit();
    }

}
