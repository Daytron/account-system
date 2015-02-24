/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.ui;

import com.gilera.ryan.accountsystem.account.BaseAccount;
import com.gilera.ryan.accountsystem.account.BusinessAccount;
import com.gilera.ryan.accountsystem.account.CashInvestmentAccount;
import com.gilera.ryan.accountsystem.account.ChildAccount;
import com.gilera.ryan.accountsystem.account.Client;
import com.gilera.ryan.accountsystem.account.CurrentAccount;
import com.gilera.ryan.accountsystem.account.IRAccount;
import com.gilera.ryan.accountsystem.account.InternationalAccount;
import com.gilera.ryan.accountsystem.account.SMBAccount;
import com.gilera.ryan.accountsystem.account.SavingsAccount;
import com.gilera.ryan.accountsystem.account.StudentAccount;
import com.gilera.ryan.accountsystem.utility.Money;
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;
import com.gilera.ryan.accountsystem.task.ScheduledTask;
import com.gilera.ryan.accountsystem.utility.StringUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

/**
 *
 * @author Ryan Gilera
 */
public final class Menu {

    private final Map<String, Integer> optionsForMainMenu;
    private final Map<String, Integer> optionsForNewAccountMenu;
    private final Map<String, Integer> optionsForViewTransactionsMenu;
    private final Map<String, Integer> optionsForTransactionTypesMenu;

    private static final int TOTAL_MAIN_MENU_OPTIONS = 9;
    private static final int TOTAL_ACCOUNT_OPTIONS = 10;
    private static final int TOTAL_VIEW_TRANSACTIONS_OPTIONS = 6;
    private static final int TOTAL_TRANSACTION_TYPE_OPTIONS = 7;
    private static final long INITIAL_ACCOUNT_NUMBER = 1000000;
    private static final int SEPARATOR_LENGTH = 47;

    private final Scanner input;
    private final List<BaseAccount> listOfAccounts;
    private long accountNumberCounter;
    private final ScheduledTask scheduledTask;

    private Menu() {
        this.optionsForMainMenu = new HashMap<>();
        // Populate options from 0 to (TOTAL_MAIN_MENU_OPTIONS - 1)
        // This is for main menu options
        // For input verification purposes later on in the menu selection
        for (int i = 0; i < TOTAL_MAIN_MENU_OPTIONS; i++) {
            this.optionsForMainMenu.put(Integer.toString(i), i);
        }

        // Populate options from 1 to TOTAL_ACCOUNT_TYPES
        // This is new account creation menu
        // For input verification purposes later on in the menu selection
        this.optionsForNewAccountMenu = new HashMap<>();
        for (int i = 0; i < TOTAL_ACCOUNT_OPTIONS; i++) {
            this.optionsForNewAccountMenu.put(Integer.toString(i), i);
        }

        // Populate options from 1 to TOTAL_VIEW_TRANSACTIONS_OPTIONS
        // This is for sub menu options on viewing transaction logs
        // For input verification purposes later on in the menu selection
        this.optionsForViewTransactionsMenu = new HashMap<>();
        for (int i = 0; i < TOTAL_VIEW_TRANSACTIONS_OPTIONS; i++) {
            this.optionsForViewTransactionsMenu.put(Integer.toString(i), i);
        }

        // Populate options from 1 to TOTAL_VIEW_TRANSACTIONS_OPTIONS
        // This is for sub menu options on viewing transaction logs
        // For input verification purposes later on in the menu selection
        this.optionsForTransactionTypesMenu = new HashMap<>();
        for (int i = 0; i < TOTAL_TRANSACTION_TYPE_OPTIONS; i++) {
            this.optionsForTransactionTypesMenu.put(Integer.toString(i), i);
        }

        this.input = new Scanner(System.in);

        // Create a synchronised arraylist
        // This data structure must be synchronized when access in multi threads
        this.listOfAccounts = Collections
                .synchronizedList(new ArrayList<BaseAccount>());

        // Fix at 7 digits
        // Starts at +1
        this.accountNumberCounter = INITIAL_ACCOUNT_NUMBER;

        // Get the single and only instance of ScheduleTask class
        this.scheduledTask = ScheduledTask.getInstance();

        // Inject or pass the listOfAccounts to the instance
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

        // Display title
        System.out.println(ConstantString.WELCOME_TITLE.getText());
        System.out.println(ConstantString.WELCOME_MESSAGE.getText());

        while (!isFinish) {

            displayMainSeparator();
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

                // Add account holder
                case 6:
                    addAccountHolder();
                    break;

                // Show all accounts Held by a customer
                case 7:
                    showAccountsHeldByAClient();
                    break;

                // View transactions
                case 8:
                    viewTransactions();
                    break;

                // Exit
                case 0:
                    System.out.print(ConstantString.CONFIRM_EXIT_QUESTION.getText());
                    String response = input.nextLine();

                    if (response.equalsIgnoreCase("y")) {
                        isFinish = true;
                        isPressEnterKeySkip = true;
                    }
                    break;

            }

            if (!isPressEnterKeySkip) {
                pressEnterKeyToContinue();
            }

        }

        // Closing message
        displayMainSeparator();
        System.out.println(ConstantString.END_MESSAGE.getText());
        displayMainSeparator();
        System.out.println("");

    }

    private void pressEnterKeyToContinue() {
        System.out.print("\n\nPress Enter to continue...");
        input.nextLine();
    }

    private void displayMainSeparator() {
        System.out.println("");
        for (int i = 0; i < SEPARATOR_LENGTH; i++) {
            System.out.print(ConstantString.MENU_SEPARATOR_MAIN.getText());
        }
        System.out.println("");
    }

    private void displayResultSeparator() {
        System.out.println("");
        for (int i = 0; i < SEPARATOR_LENGTH; i++) {
            System.out.print(ConstantString.MENU_SEPARATOR_RESULT.getText());
        }
        System.out.println("");
    }

    private void createNewAccount() {
        String clientName;

        // Display new account options
        System.out.println(ConstantString.MENU_NEW_ACCOUNTS.getText());
        // Get user option
        int userOptionResponse = processInputForMenuOptions(this.optionsForNewAccountMenu);

        if (userOptionResponse == 0) {
            System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
            return;
        }

        // Get client full name
        clientName = processInputForClientName(
                ConstantString.ENTER_CLIENT_NAME.getText());

        if (clientName == null) {
            return;
        }

        Client aClient = retrieveClient(clientName);

        // If this is a new client, create new record
        if (aClient == null) {
            System.out.println(ConstantString.CREATE_NEW_CLIENT.getText());
            aClient = new Client(clientName);
        } else {
            displayResultSeparator();
            System.out.println(
                    ConstantString.SUCCESS_CLIENT_FOUND.getText());
        }

        System.out.println(ConstantString.CREATE_NEW_ACCOUNT_MSG.getText());

        // Prepare new account number
        this.accountNumberCounter += 1;
        final String newAccountNumber
                = Long.toString(this.accountNumberCounter);

        switch (userOptionResponse) {
            case 1:
                CurrentAccount currentAccount = new CurrentAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(currentAccount);
                }
                displayResultForNewAccountCreation(currentAccount);
                break;
            case 2:
                SavingsAccount savingsAccount = new SavingsAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(savingsAccount);
                }
                displayResultForNewAccountCreation(savingsAccount);
                break;
            case 3:
                StudentAccount studentAccount = new StudentAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(studentAccount);
                }
                displayResultForNewAccountCreation(studentAccount);
                break;
            case 4:
                BusinessAccount businessAccount = new BusinessAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(businessAccount);
                }
                displayResultForNewAccountCreation(businessAccount);
                break;
            case 5:
                SMBAccount sMBAccount = new SMBAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(sMBAccount);
                }
                displayResultForNewAccountCreation(sMBAccount);
                break;
            case 6:
                IRAccount iRAccount = new IRAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(iRAccount);
                }
                displayResultForNewAccountCreation(iRAccount);
                break;

            case 7:
                CashInvestmentAccount cashInvestmentAccount = new CashInvestmentAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(cashInvestmentAccount);
                }
                displayResultForNewAccountCreation(cashInvestmentAccount);
                break;

            case 8:
                ChildAccount childAccount = new ChildAccount(
                        aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(childAccount);
                }
                displayResultForNewAccountCreation(childAccount);
                break;

            case 9:
                InternationalAccount internationalAccount
                        = new InternationalAccount(
                                aClient, newAccountNumber);
                synchronized (this.listOfAccounts) {
                    this.listOfAccounts.add(internationalAccount);
                }
                displayResultForNewAccountCreation(internationalAccount);
                break;

            default:
                break;
        }

    }

    private void displayResultForNewAccountCreation(BaseAccount account) {
        displayResultSeparator();

        System.out.println(ConstantString.SUCCESS_ACCOUNT_CREATED.getText());
        System.out.println(account);

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
        displayResultSeparator();
        System.out.println(ConstantString.SUCCESS_DEPOSIT.getText());
        System.out.println(
                "\nDetailed Summary:"
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
            displayResultSeparator();
            // Error message for overlimit withdrawals
            System.out.println(ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART1.getText()
                    + accountToWithdraw.getAccountType().getText().toLowerCase()
                    + ConstantString.ERROR_OVERLIMIT_MAX_WITHDRAW_PART2.getText()
                    + accountToWithdraw.getAccountType().getMaxWithdrawalStr()
                    + ".");
            System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
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

            displayResultSeparator();
            System.out.println(ConstantString.SUCCESS_WITHDRAWAL.getText());
            System.out.println(
                    "\nDetailed Summary:"
                    + "\nAccount number: " + accountToWithdraw.getAccountNum()
                    + "\nAmount withdrawn: " + amountToWithdraw.toString()
                    + "\nBalance: " + accountToWithdraw.getBalance().toString());
        } else {
            displayResultSeparator();
            // display overlimit overdraft error message
            System.out.println(
                    ConstantString.ERROR_OVERLIMIT_OVERDRAFT_WITHDRAWAL.getText());
            System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
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

        // Cancel operation if the latter account is the same with the 
        // initial account
        if (accountFrom.getAccountNum().equalsIgnoreCase(
                accountTo.getAccountNum())) {
            displayResultSeparator();
            System.out.println(
                    ConstantString.ERROR_SAME_ACCOUNT_TRANSFER.getText());
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
            System.out.println(ConstantString.ERROR_INSUFFICIENT_AMOUNT_TO_TRANSFER.getText());
            System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
            return;
        }

        displayResultSeparator();
        System.out.println(ConstantString.SUCCESS_TRANSFER.getText());

    }

    private void payWithInterest() {
        boolean isEmpty;

        synchronized (this.listOfAccounts) {
            isEmpty = this.listOfAccounts.isEmpty();
        }

        if (isEmpty) {
            System.out.println(
                    ConstantString.ERROR_PAY_WITH_INTEREST_EMPTY_LIST.getText());
            System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
            return;
        }

        synchronized (this.listOfAccounts) {
            Iterator<BaseAccount> iterator = this.listOfAccounts.iterator();

            while (iterator.hasNext()) {
                iterator.next().payWithInterest();
            }
        }

        displayResultSeparator();
        System.out.println(ConstantString.SUCCESS_PAY_INTEREST_MANUALLY.getText());
    }

    private void viewTransactions() {
        BaseAccount accountToViewTransaction;

        // Display sub menus
        System.out.println(ConstantString.MENU_VIEW_TRANSACTIONS.getText());

        // Get user option
        int userOptionResponse = processInputForMenuOptions(
                this.optionsForViewTransactionsMenu);

        boolean isEmptyList;
        synchronized (this.listOfAccounts) {
            isEmptyList = this.listOfAccounts.isEmpty();
        }

        if (isEmptyList) {
            displayResultSeparator();
            System.out.println(ConstantString.ERROR_ALL_TRANSACTIONS_EMPTY.getText());
            System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
            return;
        }

        switch (userOptionResponse) {
            case 0:
                displayResultSeparator();
                System.out.println(
                        ConstantString.ERROR_OPERATION_CANCELLED.getText());
                break;

            case 1:
                displayResultSeparator();
                System.out.println(ConstantString.SUCCESS_VIEW_TRANSACTIONS_ALL.getText());

                synchronized (this.listOfAccounts) {
                    Iterator<BaseAccount> iterator = this.listOfAccounts.iterator();

                    while (iterator.hasNext()) {
                        displayTransactionsForAnAccount(iterator.next());
                    }
                }

                break;

            case 2:
                accountToViewTransaction = processInputForAccountNumber(
                        ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

                if (accountToViewTransaction == null) {
                    return;
                }

                displayTransactionsForAnAccount(accountToViewTransaction);

                break;

            case 3:
                accountToViewTransaction = processInputForAccountNumber(
                        ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

                if (accountToViewTransaction == null) {
                    return;
                }

                // Check if account transactions is empty
                if (accountToViewTransaction.isTransactionsEmpty()) {
                    System.out.println(
                            ConstantString.ERROR.getText());
                    System.out.println(
                            ConstantString.ERROR_TRANSACTION_EMPTY.getText());
                    return;
                }

                // Get 1st date
                Date dateFrom = processInputForDate(
                        ConstantString.ENTER_TRANSACTION_DATE_TO.getText());

                if (dateFrom == null) {
                    return;
                }

                Calendar c = Calendar.getInstance();
                c.setTime(dateFrom);
                c.add(Calendar.DATE, -1);  // Less 1 day from dateFrom
                Date dateFromMinus1Day = c.getTime();

                // Get 2nd date
                Date dateTo = processInputForDate(
                        ConstantString.ENTER_TRANSACTION_DATE_FROM.getText());

                if (dateTo == null) {
                    return;
                }

                c.setTime(dateTo);
                c.add(Calendar.DATE, 1);  // Add 1 day to dateTo
                Date dateToPlus1Day = c.getTime();

                // if date 2 is before date 1, cancel operation
                if (dateTo.before(dateFrom)) {
                    displayResultSeparator();
                    System.out.println(
                            ConstantString.ERROR_REVERSED_DATE_RANGE.getText());
                    System.out.println(
                            ConstantString.ERROR_OPERATION_CANCELLED.getText());
                    return;
                }

                // if both dates are the same, cancel operation
                if (dateTo.equals(dateFrom)) {
                    displayResultSeparator();
                    System.out.println(
                            ConstantString.ERROR_BOTH_DATES_SAME.getText());
                    System.out.println(
                            ConstantString.ERROR_OPERATION_CANCELLED.getText());
                    return;
                }

                // Display transactions
                displayResultTransactionsSingle(accountToViewTransaction);
                boolean isNotEmptyTransaction = false;
                for (Transaction aTransaction
                        : accountToViewTransaction.getTransactions()) {

                    if (aTransaction.getDateOfTransaction()
                            .after(dateFromMinus1Day)
                            && aTransaction.getDateOfTransaction()
                            .before(dateToPlus1Day)) {
                        System.out.println(aTransaction);
                        isNotEmptyTransaction = true;
                    }

                }

                // If no transactions found within those dates,
                // display empty message
                if (!isNotEmptyTransaction) {
                    System.out.println(ConstantString.FAIL_NO_TRANSACTIONS_FOUND_BY_DATES.getText());
                    System.out.println("Date 1: " + dateFrom);
                    System.out.println("Date 2: " + dateTo);
                    System.out.println("Cancelling operation...");
                }

                break;

            case 4:

                // Display sub menus
                System.out.println(ConstantString.MENU_VIEW_TRANSACTION_TYPES.getText());

                // Get user option
                int userResponse = processInputForMenuOptions(
                        this.optionsForTransactionTypesMenu);

                // If zero is selected, cancel operation
                if (userResponse == 0) {
                    displayResultSeparator();
                    System.out.println(
                            ConstantString.ERROR_OPERATION_CANCELLED.getText());
                    return;
                }

                accountToViewTransaction = processInputForAccountNumber(
                        ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

                if (accountToViewTransaction == null) {
                    return;
                }

                // Check if account transactions is empty
                if (accountToViewTransaction.isTransactionsEmpty()) {
                    System.out.print(ConstantString.ERROR.getText());
                    System.out.println(
                            ConstantString.ERROR_TRANSACTION_EMPTY.getText());
                    System.out.println(
                            ConstantString.ERROR_OPERATION_CANCELLED.getText());
                    return;
                }

                TransactionType transactionTypeToView = null;

                // Note: Case 0 is already filtered on top in order to skip
                // early before the system ask for account number (see above)
                // No default option because user response is carefuly filtered
                // by processInputForMenuOptions() method
                switch (userResponse) {
                    case 1:
                        transactionTypeToView = TransactionType.DEPOSIT;
                        break;
                    case 2:
                        transactionTypeToView = TransactionType.VIEW_BALANCE;
                        break;
                    case 3:
                        transactionTypeToView = TransactionType.WITHDRAW;
                        break;
                    case 4:
                        transactionTypeToView = TransactionType.TRANSFER;
                        break;
                    case 5:
                        transactionTypeToView = TransactionType.PAID_INTEREST;
                        break;
                    case 6:
                        transactionTypeToView = TransactionType.PAID_OVERDRAFT_PENALTY;
                        break;
                }

                // Display transactions
                displayResultTransactionsSingle(accountToViewTransaction);

                boolean isNotEmpty = false;
                for (Transaction aTransaction
                        : accountToViewTransaction.getTransactions()) {
                    if (aTransaction.getTransactionType() == transactionTypeToView) {
                        System.out.println(aTransaction);
                        isNotEmpty = true;
                    }
                }

                // If no transactions based on type is found, display fail message
                if (!isNotEmpty) {
                    System.out.println(ConstantString.FAIL_NO_TRANSACTIONS_FOUND_BY_TYPE.getText());
                }

                break;

            case 5:
                String clientName = processInputForClientName(
                        ConstantString.ENTER_CLIENT_NAME.getText());

                if (clientName == null) {
                    return;
                }

                Client client = retrieveClient(clientName);

                if (client == null) {
                    return;
                } else {
                    displayResultSeparator();
                    System.out.println(ConstantString.SUCCESS_CLIENT_FOUND.getText());
                }

                synchronized (this.listOfAccounts) {
                    Iterator<BaseAccount> iterator = this.listOfAccounts.iterator();

                    while (iterator.hasNext()) {
                        BaseAccount account = iterator.next();

                        for (Client aClient : account.getListOfAccountHolders()) {
                            if (aClient.isEqualTo(client)) {
                                displayTransactionsForAnAccount(account);
                            }
                        }

                    }
                }

                break;
        }

    }

    private void displayTransactionsForAnAccount(BaseAccount accountToView) {
        displayResultTransactionsSingle(accountToView);

        if (accountToView.getTransactions().isEmpty()) {
            System.out.println(
                    ConstantString.ERROR_TRANSACTION_EMPTY.getText());
        } else {
            for (Transaction aTransaction : accountToView.getTransactions()) {
                System.out.println(aTransaction);
            }
        }
    }

    private void displayResultTransactionsSingle(BaseAccount accountToView) {
        displayResultSeparator();
        System.out.println(ConstantString.SUCCESS_VIEW_TRANSACTIONS_SINGLE.getText());

        for (Client aClient : accountToView.getListOfAccountHolders()) {
            System.out.println("Account name: " + aClient.getName());
        }
        System.out.println("Account number: " + accountToView.getAccountNum() + "\n");
    }

    private void addAccountHolder() {
        Client client, previousClient = null;

        String name = processInputForClientName(
                ConstantString.ENTER_CLIENT_NAME.getText());

        name = StringUtil.formatClientName(name);

        boolean isAlreadyAClient = false;
        synchronized (this.listOfAccounts) {
            Iterator<BaseAccount> iterator = this.listOfAccounts.iterator();

            while (iterator.hasNext()) {
                BaseAccount account = iterator.next();

                if (!isAlreadyAClient) {
                    for (Client aClient : account.getListOfAccountHolders()) {
                        if (aClient.getName().toLowerCase()
                                .equalsIgnoreCase(name.toLowerCase())) {
                            isAlreadyAClient = true;
                            previousClient = aClient;
                            break;
                        }
                    }
                } else {
                    break;
                }

            }
        }
        
        if (isAlreadyAClient) {
            displayResultSeparator();
            System.out.println(
                ConstantString.SUCCESS_CLIENT_FOUND.getText());
            System.out.println(previousClient);
            System.out.println("Is this the client you're looking for? (y/n):");
            String response = this.input.nextLine();
            
            if (!response.equalsIgnoreCase("y")) {
                displayResultSeparator();
                System.out.println(""); 
                System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
                return;
            } else {
                client = previousClient;
            }
        } else {
            client = new Client(name);
        }
        
        BaseAccount accountInvolved = processInputForAccountNumber(
                ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());
        
        if (accountInvolved == null) {
            return;
        }
        
        final int addClientResult = accountInvolved.addAccountHolder(client);
        
        displayResultSeparator();
        switch (addClientResult) {
            case 1:
                System.out.println("Error! The account is already full, cannot add "
                        + "further client.");
                System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
                break;
                
            case 2:
                System.out.println("Error! The person is already registered on"
                        + " that account. You cannot add twice.");
                System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
                break;
            case 0:
                System.out.println("Successfully added a client.");
                System.out.println(accountInvolved);
        }
    }

    private void showAccountsHeldByAClient() {
        String clientName = processInputForClientName(
                ConstantString.ENTER_CLIENT_NAME.getText());

        if (clientName == null) {
            return;
        }

        Client client = retrieveClient(clientName);

        if (client == null) {
            return;
        } else {
            displayResultSeparator();
            System.out.print(ConstantString.SUCESS_VIEW_ACCOUNT_BY_NAME.getText());
        }

        synchronized (this.listOfAccounts) {
            Iterator<BaseAccount> iterator = this.listOfAccounts.iterator();

            while (iterator.hasNext()) {
                BaseAccount account = iterator.next();

                for (Client aClient : account.getListOfAccountHolders()) {
                    if (aClient.isEqualTo(client)) {
                        displayResultSeparator();
                        System.out.println(account);
                    }
                }

            }
        }

    }

    private Client retrieveClient(String name) {
        name = StringUtil.formatClientName(name);

        synchronized (this.listOfAccounts) {
            Iterator<BaseAccount> iterator = this.listOfAccounts.iterator();

            while (iterator.hasNext()) {
                BaseAccount account = iterator.next();

                for (Client aClient : account.getListOfAccountHolders()) {
                    if (aClient.getName().toLowerCase()
                            .equalsIgnoreCase(name.toLowerCase())) {
                        return aClient;
                    }
                }

            }
        }

        displayResultSeparator();
        System.out.println(ConstantString.ERROR_NO_CLIENT_FOUND.getText());
        System.out.println(
                ConstantString.ERROR_OPERATION_CANCELLED.getText());
        return null;

    }

    private void viewBalance() {
        BaseAccount accountToViewBalance;

        accountToViewBalance = processInputForAccountNumber(
                ConstantString.ENTER_ACCOUNT_NUM_DEFAULT.getText());

        if (accountToViewBalance == null) {
            return;
        }

        displayResultSeparator();
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
            System.out.print(ConstantString.ENTER_OPTION.getText()
                    + (listOfOptions.size() - 1) + "]: ");
            optionString = this.input.nextLine();

            userOptionResponse = listOfOptions.get(optionString);
            if (userOptionResponse != null) {
                break;
            } else {
                displayResultSeparator();
                System.out.println(ConstantString.ERROR_INVALID_OPTION.getText());
            }
        }
        return userOptionResponse;
    }

    private BaseAccount processInputForAccountNumber(String messageToUser) {
        String accountNumber;

        System.out.print(messageToUser);

        try {
            accountNumber = this.input.nextLine();

            synchronized (this.listOfAccounts) {
                Iterator<BaseAccount> iterator = this.listOfAccounts.iterator();

                while (iterator.hasNext()) {
                    BaseAccount account = iterator.next();

                    if (account.getAccountNum().equals(accountNumber)) {
                        return account;
                    }
                }
            }

        } catch (Exception ex) {
        }

        displayResultSeparator();
        System.out.println(ConstantString.ERROR_INVALID_ACCOUNT_NUMBER.getText());
        System.out.println(
                ConstantString.ERROR_OPERATION_CANCELLED.getText());
        return null;

    }

    private Money processInputForMoney(String messageToUser) {
        Money amountGiven;

        System.out.print(messageToUser);
        String amountGivenStr = this.input.nextLine();

        try {
            amountGiven = Money.valueOf(amountGivenStr);

            if (amountGiven != null) {
                return amountGiven;
            }
        } catch (Exception e) {
        }

        displayResultSeparator();
        System.out.println(ConstantString.ERROR_INVALID_MONEY.getText());
        System.out.println(
                ConstantString.ERROR_OPERATION_CANCELLED.getText());
        return null;

    }

    private Date processInputForDate(String messageToUser) {
        Date date;
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        System.out.print(messageToUser);
        String dateToParse = this.input.nextLine();

        try {
            date = simpleDateFormat.parse(dateToParse);

            if (date != null) {
                return date;
            }
        } catch (Exception e) {
        }

        displayResultSeparator();
        System.out.println(ConstantString.ERROR_INVALID_DATE_INPUT.getText());
        System.out.println(
                ConstantString.ERROR_OPERATION_CANCELLED.getText());
        return null;

    }

    private String processInputForClientName(String instructionMsg) {
        String clientName;

        System.out.print(instructionMsg);
        clientName = this.input.nextLine();

        // \\p{L} find matches for any kind of letter from all types of languages
        // Allows not only letters but any special character given to a name
        // like ' and -. Allows spaces in between names
        if (clientName.matches("^[\\p{L} .'-]+$")) {
            // First it trims extra spaces in the beginning and end
            // Replaces all 1 or more spaces in between to single space
            // Ex. "ryan    gilera   " becomes "ryan gilera"
            clientName = StringUtil.removeExtraSpacesInName(clientName);

            return clientName;
        } else {
            displayResultSeparator();
            System.out.println(ConstantString.ERROR_INVALID_NAME.getText());
            System.out.println(
                    ConstantString.ERROR_OPERATION_CANCELLED.getText());
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
