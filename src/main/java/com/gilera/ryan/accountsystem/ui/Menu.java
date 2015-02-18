/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.ui;

import com.gilera.ryan.accountsystem.account.AccountType;
import com.gilera.ryan.accountsystem.account.BaseAccount;
import com.gilera.ryan.accountsystem.account.BusinessAccount;
import com.gilera.ryan.accountsystem.account.CashInvestmentAccount;
import com.gilera.ryan.accountsystem.account.ChildAccount;
import com.gilera.ryan.accountsystem.account.CurrentAccount;
import com.gilera.ryan.accountsystem.account.IRAccount;
import com.gilera.ryan.accountsystem.account.InternationalAccount;
import com.gilera.ryan.accountsystem.account.SMBAccount;
import com.gilera.ryan.accountsystem.account.SavingsAccount;
import com.gilera.ryan.accountsystem.account.StudentAccount;
import com.gilera.ryan.accountsystem.asset.Money;
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;
import com.gilera.ryan.accountsystem.task.ScheduledTask;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

/**
 *
 * @author Ryan Gilera
 */
public class Menu {

    private final Map<String, Integer> optionsForMainMenu;
    private final Map<String, Integer> optionsForNewAccountMenu;
    private final Map<String, Integer> optionsForViewTransactionsMenu;
    private static final int TOTAL_MAIN_MENU_OPTIONS = 10;
    private static final int TOTAL_NEW_ACCOUNT_OPTIONS = 10;
    private static final int TOTAL_VIEW_TRANSACTIONS_OPTIONS = 5;
    private static final long INITIAL_ACCOUNT_NUMBER = 1000000;
    private static final long INITIAL_CLIENT_ID = 0;

    private final Scanner input;
    private final ArrayList<BaseAccount> listOfAccounts;
    private long newReadyAccountNumberToUse;
    private long newReadyClientIDToUse;
    private final ScheduledTask scheduledTask;

    private Menu() {
        this.optionsForMainMenu = new HashMap<>();
        // Populate options from 0 to (TOTAL_MAIN_MENU_OPTIONS - 1)
        // This is for input verification later on in the menu selection
        for (int i = 0; i < TOTAL_MAIN_MENU_OPTIONS; i++) {
            this.optionsForMainMenu.put(Integer.toString(i), i);
        }

        // Populate options from 1 to TOTAL_ACCOUNT_TYPES
        // This is for input verification later on in the menu selection
        this.optionsForNewAccountMenu = new HashMap<>();
        for (int i = 0; i < TOTAL_NEW_ACCOUNT_OPTIONS; i++) {
            this.optionsForNewAccountMenu.put(Integer.toString(i), i);
        }

        this.optionsForViewTransactionsMenu = new HashMap<>();
        for (int i = 0; i < TOTAL_VIEW_TRANSACTIONS_OPTIONS; i++) {
            this.optionsForViewTransactionsMenu.put(Integer.toString(i), i);
        }

        this.input = new Scanner(System.in);
        this.listOfAccounts = new ArrayList<>();
        // Fix at 7 digits
        // Starts at +1
        this.newReadyAccountNumberToUse = INITIAL_ACCOUNT_NUMBER;
        // Starts at +1
        this.newReadyClientIDToUse = INITIAL_CLIENT_ID;

        this.scheduledTask = ScheduledTask.getInstance();
        this.scheduledTask.setAccounts(listOfAccounts);

        Timer updateEvery3Minutes = new Timer(true);

        // An interval rate of every 3 minutes with initital delay of 3 minutes
        // 2nd parameter is the one time initial delay, 3rd is interval
        updateEvery3Minutes.scheduleAtFixedRate(scheduledTask,
                ScheduledTask.getINTERVAL_TIME(),
                ScheduledTask.getINTERVAL_TIME());
    }

    public void launch() {
        boolean isFinish = false;
        boolean isPressEnterKeySkip = false;
        int userOptionResponse;

        while (!isFinish) {

            displayMenuSeparator();
            System.out.println(ConstantString.MENU_MAIN.getText());
            userOptionResponse = processInputForMenuOptions(this.optionsForMainMenu);

            switch (userOptionResponse) {
                // Create new account
                case 1:
                    createNewAccount();
                    break;
                // Deposit    
                case 2:
                    deposit();
                    break;
                // View balance
                case 3:
                    viewBalance();
                    break;
                // Withdraw
                case 4:
                    withdraw();
                    break;

                // Transfer
                case 5:
                    transferMoney();
                    break;

                // Pay interest
                case 6:
                    payWithInterest();
                    break;

                // Add account holder
                case 7:
                    addAccountHolder();
                    break;

                // Show all accounts Held by a customer
                case 8:
                    showAccountsHeldByAClient();
                    break;

                // View transactions
                case 9:
                    viewTransactions();
                    break;

                // Exit
                case 0:
                    System.out.println(ConstantString.CONFIRM_EXIT_QUESTION.getText());
                    String response = input.nextLine();
                    if (response.equalsIgnoreCase("y")) {
                        isFinish = true;
                        isPressEnterKeySkip = true;
                        break;
                    }
                    break;

            }

            if (!isPressEnterKeySkip) {
                pressAnyKeyToContinue();
            }

        }

        displayMenuSeparator();
        System.out.println(ConstantString.END_MESSAGE.getText());

    }

    private void pressAnyKeyToContinue() {
        System.out.println("\nPress Enter to continue...");
        input.nextLine();
    }

    private void displayMenuSeparator() {
        System.out.println(ConstantString.MENU_SEPARATOR_MAIN.getText());
    }

    private void displayMenuResultSeparator() {
        System.out.println(ConstantString.MENU_SEPARATOR_RESULT.getText());
    }

    private void createNewAccount() {
        String clientName;
        long clientID;

        // Display new account options
        System.out.println(ConstantString.MENU_NEW_ACCOUNTS.getText());
        // Get user option
        int userOptionResponse = processInputForMenuOptions(this.optionsForNewAccountMenu);

        if (userOptionResponse == 0) {
            System.out.println(
                    ConstantString.CANCEL_NEW_ACCOUNT.getText());
            return;
        }

        // Get client full name
        clientName = processInputForCustomerName(
                ConstantString.ENTER_CLIENT_NAME.getText());

        if (clientName == null) {
            return;
        }

        // Update with new client ID
        this.newReadyClientIDToUse += 1;
        clientID = this.newReadyClientIDToUse;

        // Prepare new account number
        this.newReadyAccountNumberToUse += 1;

        switch (userOptionResponse) {
            case 1:
                CurrentAccount currentAccount = new CurrentAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(currentAccount);
                displayResultForNewAccountCreation(currentAccount);
                break;
            case 2:
                SavingsAccount savingsAccount = new SavingsAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(savingsAccount);
                displayResultForNewAccountCreation(savingsAccount);
                break;
            case 3:
                StudentAccount studentAccount = new StudentAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(studentAccount);
                displayResultForNewAccountCreation(studentAccount);
                break;
            case 4:
                BusinessAccount businessAccount = new BusinessAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(businessAccount);
                displayResultForNewAccountCreation(businessAccount);
                break;
            case 5:
                SMBAccount sMBAccount = new SMBAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(sMBAccount);
                displayResultForNewAccountCreation(sMBAccount);
                break;
            case 6:
                IRAccount iRAccount = new IRAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(iRAccount);
                displayResultForNewAccountCreation(iRAccount);
                break;

            case 7:
                CashInvestmentAccount cashInvestmentAccount = new CashInvestmentAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(cashInvestmentAccount);
                displayResultForNewAccountCreation(cashInvestmentAccount);
                break;

            case 8:
                ChildAccount childAccount = new ChildAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(childAccount);
                displayResultForNewAccountCreation(childAccount);
                break;

            case 9:
                InternationalAccount internationalAccount = 
                        new InternationalAccount(
                        clientName, newReadyAccountNumberToUse, clientID);
                listOfAccounts.add(internationalAccount);
                displayResultForNewAccountCreation(internationalAccount);
                break;

            default:
                break;
        }

    }

    private void displayResultForNewAccountCreation(BaseAccount account) {
        displayMenuResultSeparator();

        System.out.println("A "
                + account.getAccountType().getText().toLowerCase()
                + " account has been created.");
        displayAccountDetails(account);

    }
    
    private void displayAccountDetails(BaseAccount accountToDisplay) {
        System.out.println(
                "\nDetailed summary:\n"
                + "Client Name: "
                + accountToDisplay.getHolderName()
                + "\nAccount Number:"
                + accountToDisplay.getAccountNum()
                + "\nCustomer ID: "
                + accountToDisplay.getCustomerID()
                + "\nBalance: "
                + accountToDisplay.getBalance().toString()
                + "\n\nAccount Type: "
                + accountToDisplay.getAccountType().getText()
                + "\nInterest rate: " 
                + accountToDisplay.getInterestRateInPercentage() + "%"
                + "\nMax Daily Withdrawal: "
                + accountToDisplay.getAccountType().getMaxWithdrawalStr() 
                + "\nOverdraft Limit: "
                + accountToDisplay.getAccountType().getOverdraftLimit()
                + "\n");
    }

    private void deposit() {
        Money amountToDeposit;
        BaseAccount accountToDeposit;

        // Get account to deposit
        accountToDeposit = processInputForAccountNumber(
                ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

        if (accountToDeposit == null) {
            return;
        }

        // Get amount to deposit                 
        amountToDeposit = processInputForMoney(
                ConstantString.ENTER_DEPOSIT_AMOUNT.getText());

        if (amountToDeposit == null) {
            return;
        }

        accountToDeposit.deposit(amountToDeposit);
        accountToDeposit.addTransaction(new Date(),
                TransactionType.DEPOSIT, amountToDeposit,
                accountToDeposit.getBalance());

        // Result Message
        displayMenuResultSeparator();
        System.out.println("Deposit successful."
                + "\nDetailed Summary:"
                + "\nAccount number: " + accountToDeposit.getAccountNum()
                + "\nAmount deposited: " + amountToDeposit.toString()
                + "\nBalance: " + accountToDeposit.getBalance().toString());

    }

    private void withdraw() {
        Money amountToWithdraw;
        BaseAccount accountToWithdraw;

        // Get account
        accountToWithdraw = processInputForAccountNumber(
                ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

        if (accountToWithdraw == null) {
            return;
        }

        // Get amount to withdraw              
        amountToWithdraw = processInputForMoney(
                ConstantString.ENTER_WITHDRAW_AMOUNT.getText());

        if (amountToWithdraw == null) {
            return;
        }

        // Check if amount has reached max amount per withdrawal
        if (amountToWithdraw.isGreaterThan(
                accountToWithdraw.getAccountType().getMaxWithdrawal())) {
            displayMenuResultSeparator();
            // Error message for overlimit withdrawals
            System.out.println(ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART1.getText()
                    + accountToWithdraw.getAccountType().getText().toLowerCase()
                    + ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART2.getText()
                    + accountToWithdraw.getAccountType().getMaxWithdrawalStr()
                    + ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART3.getText());
            return;
        }

        // Process withdrawals
        // Condition if ((balance - amountToWithdraw) >= limit)
        Money newPotentialBalance = accountToWithdraw.getBalance().minus(amountToWithdraw);
        if (newPotentialBalance.isGreaterThanOrEqualTo(
                accountToWithdraw.getAccountType().getOverdraftLimit())) {
            accountToWithdraw.withdraw(amountToWithdraw);
            accountToWithdraw.addTransaction(new Date(),
                    TransactionType.WITHDRAW, amountToWithdraw,
                    accountToWithdraw.getBalance());

            displayMenuResultSeparator();
            System.out.println(ConstantString.SUCCESS_WITHDRAWAL.getText());
        } else {
            displayMenuResultSeparator();
            // display overlimit overdraft error message
            System.out.println(
                    ConstantString.ERROR_OVERLIMIT_OVERDRAFT_WITHDRAWAL.getText());

        }

    }

    private void transferMoney() {
        Money amountToTransfer;
        BaseAccount accountFrom, accountTo;

        // Get account to transfer from
        accountFrom = processInputForAccountNumber(
                ConstantString.ENTER_TRANSFER_ACCOUNT_FROM.getText());

        if (accountFrom == null) {
            return;
        }

        // Get account to transfer to
        accountTo = processInputForAccountNumber(
                ConstantString.ENTER_TRANSFER_ACCOUNT_TO.getText());

        if (accountTo == null) {
            return;
        }

        // Get amount               
        amountToTransfer = processInputForMoney(
                ConstantString.ENTER_TRANSFER_AMOUNT.getText());

        if (amountToTransfer == null) {
            return;
        }

        
        // Condition if ((balance - amountToTransfer) >= limit)
        Money newPotentialBalance = accountFrom.getBalance().minus(amountToTransfer);
        // Allows to transfer money as long the accountFrom has a balance of less than
        // or equal to the accountFrom overdraft limit
        if (newPotentialBalance.isGreaterThanOrEqualTo(
            accountFrom.getAccountType().getOverdraftLimit())) {
            accountFrom.withdraw(amountToTransfer);
            accountFrom.addTransaction(new Date(),
                    TransactionType.TRANSFER, amountToTransfer, 
                    accountFrom.getBalance());

            accountTo.deposit(amountToTransfer);
            accountTo.addTransaction(new Date(),
                    TransactionType.TRANSFER, amountToTransfer,
                    accountTo.getBalance());
        } else {
            System.out.println(ConstantString.ERROR_MSG_INSUFFICIENT_AMOUNT_TO_TRANSFER.getText());
            return;
        }

        displayMenuResultSeparator();
        System.out.println(ConstantString.SUCCESS_TRANSFER.getText());

    }

    private void payWithInterest() {
        if (listOfAccounts.isEmpty()) {
            System.out.println(
                    ConstantString.ERROR_PAY_WITH_INTEREST_EMPTY_LIST.getText());
            return;
        }

        for (BaseAccount account : listOfAccounts) {
            account.payWithInterest();
        }

        displayMenuResultSeparator();
        System.out.println(ConstantString.SUCCESS_PAY_INTEREST_MANUALLY.getText());
    }

    private void viewTransactions() {
        BaseAccount accountToViewTransaction;

        // Display sub menus
        System.out.println(ConstantString.MENU_VIEW_TRANSACTIONS.getText());

        // Get user option
        int userOptionResponse = processInputForMenuOptions(
                this.optionsForViewTransactionsMenu);
        
        if (this.listOfAccounts.isEmpty()) {
            displayMenuResultSeparator();
            System.out.println(ConstantString.ERROR_ALL_TRANSACTIONS_EMPTY.getText());
            return;
        }

        switch (userOptionResponse) {
            case 0:
                displayMenuResultSeparator();
                System.out.println(
                    ConstantString.CANCEL_VIEW_TRANSACTIONS.getText());
                break;
                
            case 1:
                displayMenuResultSeparator();
                System.out.println(ConstantString.SUCCESS_VIEW_TRANSACTIONS_ALL.getText());
                
                for (BaseAccount account : this.listOfAccounts) {
                    if (account.getTransactions().isEmpty()) {
                        System.out.println("Transaction log for: " 
                        + account.getAccountNum() + " is EMPTY.");
                    } else {
                        displayResultForViewTransactionsSingle(account);
                        for (Transaction aTransaction : account.getTransactions()) {
                            System.out.println(aTransaction);
                        }
                    }
                }
                break;

            case 2:
                accountToViewTransaction = processInputForAccountNumber(
                        ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

                if (accountToViewTransaction == null) {
                    return;
                }

                // Check if account transactions is empty
                if (accountToViewTransaction.isTransactionsEmpty()) {
                    System.out.println(
                            ConstantString.ERROR_TRANSACTION_EMPTY.getText());
                    return;
                }

                // Display transactions
                displayResultForViewTransactionsSingle(accountToViewTransaction);
                
                for (Transaction aTransaction : 
                        accountToViewTransaction.getTransactions()) {
                    System.out.println(aTransaction);
                }
                break;
        }

    }
    
    private void displayResultForViewTransactionsSingle(BaseAccount accountToView) {
        displayMenuResultSeparator();
        System.out.println(ConstantString.SUCCESS_VIEW_TRANSACTIONS_SINGLE.getText());
        System.out.println("Account name: " + accountToView.getHolderName());
        System.out.println("Account number: " + accountToView.getAccountNum() + "\n");
    }

    private void addAccountHolder() {
        System.out.println("Under review..");
        /*
         System.out.println("Enter Account Number");
         newReadyAccountNumberToUse = Integer.parseInt(input.nextLine());
         System.out.println("Enter Customer first and Last Name");
         custName = input.nextLine();

         for (BaseAccount account : accounts) {
         if (account.getAccountNum() == acc_number) {
         account.AddAccHolder(custName, newReadyAccountNumberToUse);
         break;
         }
         } */
    }

    private void showAccountsHeldByAClient() {
        BaseAccount accountToShow;

        accountToShow = processInputForAccountNumber(
                ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

        if (accountToShow == null) {
            return;
        }

        displayMenuResultSeparator();
        System.out.println(ConstantString.SUCESS_VIEW_ACCOUNT.getText());
        displayAccountDetails(accountToShow);
    }

    private void viewBalance() {
        BaseAccount accountToViewBalance;

        accountToViewBalance = processInputForAccountNumber(
                ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

        if (accountToViewBalance == null) {
            return;
        }

        displayMenuResultSeparator();
        System.out.println(
                ConstantString.SUCCESS_BALANCE.getText()
                + accountToViewBalance.getBalance());
        accountToViewBalance.addTransaction(new Date(),
                TransactionType.VIEW_BALANCE, 
                new Money(),
                accountToViewBalance.getBalance());

    }

    private int processInputForMenuOptions(Map<String, Integer> listOfOptions) {
        String optionString;
        Integer userOptionResponse;

        while (true) {
            System.out.println(ConstantString.ENTER_OPTION.getText());
            optionString = this.input.nextLine();

            userOptionResponse = listOfOptions.get(optionString);
            if (userOptionResponse != null) {
                break;
            } else {
                displayMenuResultSeparator();
                System.out.println(ConstantString.ERROR_MSG_INVALID_OPTION.getText());
            }
        }

        return userOptionResponse;
    }

    private BaseAccount processInputForAccountNumber(String messageToUser) {
        long accountNumber;

        System.out.println(messageToUser);

        try {
            accountNumber = this.input.nextLong();
            this.input.nextLine();

            for (BaseAccount anAccount : listOfAccounts) {
                if (anAccount.getAccountNum() == accountNumber) {
                    return anAccount;
                }
            }

        } catch (Exception ex) {
        }

        displayMenuResultSeparator();
        System.out.println(ConstantString.ERROR_MSG_ACCOUNT_NUMBER.getText());
        return null;

    }

    private Money processInputForMoney(String messageToUser) {
        Money amountGiven;

        System.out.println(messageToUser);
        String amountGivenStr = this.input.nextLine();

        try {
            amountGiven = Money.valueOf(amountGivenStr);

            if (amountGiven != null) {
                return amountGiven;
            }
        } catch (Exception e) {
        }

        displayMenuResultSeparator();
        System.out.println(ConstantString.ERROR_MSG_INVALID_MONEY.getText());
        return null;

    }

    private String processInputForCustomerName(String instructionMsg) {
        String clientName;

        System.out.println(instructionMsg);
        clientName = this.input.nextLine();

        if (clientName.matches("^[\\p{L} .'-]+$")) {
            return clientName;
        } else {
            displayMenuResultSeparator();
            System.out.println(ConstantString.ERROR_INVALID_NAME.getText());
            return null;
        }
    }

    public static Menu getInstance() {
        return MenuHolder.INSTANCE;
    }

    private static class MenuHolder {

        private static final Menu INSTANCE = new Menu();
    }

}
