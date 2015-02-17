/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.ui;

import com.gilera.ryan.accountsystem.account.AccountType;
import com.gilera.ryan.accountsystem.account.BaseAccount;
import com.gilera.ryan.accountsystem.account.BusinessAccount;
import com.gilera.ryan.accountsystem.account.CurrentAccount;
import com.gilera.ryan.accountsystem.account.IRAccount;
import com.gilera.ryan.accountsystem.account.SMBAccount;
import com.gilera.ryan.accountsystem.account.SavingsAccount;
import com.gilera.ryan.accountsystem.account.StudentAccount;
import com.gilera.ryan.accountsystem.asset.Money;
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Ryan Gilera
 */
public class Menu {

    private final Map<String, Integer> optionsForMainMenu;
    private final Map<String, Integer> optionsForNewAccountMenu;
    private static final int TOTAL_MAIN_MENU_OPTIONS = 10;
    private static final int TOTAL_ACCOUNT_TYPES = 7;
    private static final long INITIAL_ACCOUNT_NUMBER = 1000000;
    private static final long INITIAL_CLIENT_ID = 0;

    private final Scanner input;
    private final ArrayList<BaseAccount> accounts;
    private long newReadyAccountNumberToUse;
    private long newReadyClientIDToUse;

    public Menu() {
        this.optionsForMainMenu = new HashMap<>();
        // Populate options from 0 to (TOTAL_MAIN_MENU_OPTIONS - 1)
        // This is for input verification later on in the menu selection
        for (int i = 0; i < TOTAL_MAIN_MENU_OPTIONS; i++) {
            this.optionsForMainMenu.put(Integer.toString(i), i);
        }

        // Populate options from 1 to TOTAL_ACCOUNT_TYPES
        // This is for input verification later on in the menu selection
        this.optionsForNewAccountMenu = new HashMap<>();
        for (int i = 0; i < TOTAL_ACCOUNT_TYPES; i++) {
            this.optionsForNewAccountMenu.put(Integer.toString(i), i);
        }

        this.input = new Scanner(System.in);
        this.accounts = new ArrayList<>();
        // Fix at 7 digits
        // Starts at +1
        this.newReadyAccountNumberToUse = INITIAL_ACCOUNT_NUMBER;
        // Starts at +1
        this.newReadyClientIDToUse = INITIAL_CLIENT_ID;
    }

    public void launch() {
        boolean isFinish = false;
        boolean isPressEnterKeySkip = false;
        int userOptionResponse;

        while (!isFinish) {

            displayMenuSeparator();
            System.out.println(ConstantString.MENU.getText());
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

        System.out.println(ConstantString.END_MESSAGE.getText());

    }

    private void pressAnyKeyToContinue() {
        System.out.println("\nPress Enter to continue...");
        input.nextLine();
    }

    private void displayMenuSeparator() {
        System.out.println(ConstantString.MENU_SEPARATOR.getText());
    }

    private void displayMenuResultSeparator() {
        System.out.println(ConstantString.MENU_RESULT_SEPARATOR.getText());
    }

    private void createNewAccount() {
        String clientName;
        long clientID;

        // Display new account options
        System.out.println(ConstantString.NEW_ACCOUNTS_MENU.getText());
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
                accounts.add(new CurrentAccount(
                        clientName, newReadyAccountNumberToUse, clientID));
                displayResultForNewAccountCreation(AccountType.CURRENT);
                break;
            case 2:
                accounts.add(new SavingsAccount(
                        clientName, newReadyAccountNumberToUse, clientID));
                displayResultForNewAccountCreation(AccountType.SAVINGS);
                break;
            case 3:
                accounts.add(new StudentAccount(
                        clientName, newReadyAccountNumberToUse, clientID));
                displayResultForNewAccountCreation(AccountType.STUDENT);
                break;
            case 4:
                accounts.add(new BusinessAccount(
                        clientName, newReadyAccountNumberToUse, clientID));
                displayResultForNewAccountCreation(AccountType.BUSINESS);
                break;
            case 5:
                accounts.add(new SMBAccount(
                        clientName, newReadyAccountNumberToUse, clientID));
                displayResultForNewAccountCreation(AccountType.SMB);
                break;
            case 6:
                accounts.add(new IRAccount(
                        clientName, newReadyAccountNumberToUse, clientID));
                displayResultForNewAccountCreation(AccountType.IRA);
                break;

            default:
                break;
        }

    }

    private void displayResultForNewAccountCreation(AccountType accountType) {
        displayMenuResultSeparator();

        System.out.println("A "
                + accountType.getText().toLowerCase()
                + " account has been created. \n"
                + "\nDetailed summary:\n"
                + "Client Name: "
                + accounts.get(accounts.size() - 1).getHolderName()
                + "\nAccount Number:"
                + accounts.get(accounts.size() - 1).getAccountNum()
                + "\nCustomer ID: "
                + accounts.get(accounts.size() - 1).getCustomerID()
                + "\nDefault Balance: "
                + accounts.get(accounts.size() - 1).getBalance().toString()
                + "\nInterest Rate: "
                + accounts.get(accounts.size() - 1).getInterestRate() + " %"
                + "\nMax Daily Withdrawal: "
                + accountType.getMaxWithdrawalStr() + "\n");

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
                TransactionType.DEPOSIT, amountToDeposit);

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

        // Process withdrawals
        if (amountToWithdraw.isLessThanOrEqualTo(
                Money.valueOf(accountToWithdraw.getAccountType().getMaxWithdrawalStr()))) {
            accountToWithdraw.withdraw(amountToWithdraw);
            accountToWithdraw.addTransaction(new Date(),
                    TransactionType.WITHDRAW, amountToWithdraw);

            displayMenuResultSeparator();
            System.out.println(ConstantString.SUCCESS_WITHDRAWAL.getText());
        } else {
            displayMenuResultSeparator();
            // Error message for overlimit withdrawals
            System.out.println(ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART1
                    + accountToWithdraw.getAccountType().getText().toLowerCase()
                    + ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART2
                    + accountToWithdraw.getAccountType().getMaxWithdrawalStr()
                    + ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART3);
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

        if (accountFrom.getBalance().isGreaterThanOrEqualTo(amountToTransfer)) {
            accountFrom.withdraw(amountToTransfer);
            accountFrom.addTransaction(new Date(),
                    TransactionType.TRANSFER, amountToTransfer);

            accountTo.deposit(amountToTransfer);
            accountTo.addTransaction(new Date(),
                    TransactionType.TRANSFER, amountToTransfer);
        } else {
            System.out.println(ConstantString
                    .ERROR_MSG_INSUFFICIENT_AMOUNT_TO_TRANSFER.getText());
            return;
        }

        displayMenuResultSeparator();
        System.out.println(ConstantString.SUCCESS_TRANSFER.getText());

    }

    private void payWithInterest() {
        if (accounts.isEmpty()) {
            System.out.println(
                    ConstantString.ERROR_PAY_WITH_INTEREST_EMPTY_LIST.getText());
            return;
        }

        for (BaseAccount account : accounts) {
            account.payWithInterest();
        }

        displayMenuResultSeparator();
        System.out.println(ConstantString.SUCCESS_PAY_INTEREST_MANUALLY.getText());
    }

    private void viewTransactions() {
        BaseAccount accountToViewTransaction;

        accountToViewTransaction = processInputForAccountNumber(
                ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

        if (accountToViewTransaction == null) {
            return;
        }

        // Check if account transactions is empty
        if (accountToViewTransaction.isTransactionsEmpty()) {
            System.out.println(ConstantString.ERROR_TRANSACTION_EMPTY.getText());
            return;
        }

        // Display transactions
        displayMenuResultSeparator();
        for (Transaction aTransaction : accountToViewTransaction.getTransactions()) {
            System.out.println(
                    aTransaction.getTransactionType().getText()
                    + " " + aTransaction.getDateOfTransaction()
                    + " " + aTransaction.getAmountInString());
        }
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
        System.out.println(ConstantString.SUCESS_VIEW_TRANS_BY_CLIENT.getText()
                + "\nAccount number: " + accountToShow.getAccountNum()
                + "\nAccount type: " + accountToShow.getAccountType().getText()
                + "\nClient name: " + accountToShow.getHolderName()
                + "\nBalance: " + accountToShow.getBalance().toString());
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
                TransactionType.VIEW_BALANCE, accountToViewBalance.getBalance());

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

            for (BaseAccount anAccount : accounts) {
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
        Money money;

        System.out.println(messageToUser);
        String moneyReceivedStr = this.input.nextLine();

        try {
            money = Money.valueOf(moneyReceivedStr);

            if (money != null) {
                return money;
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

}
