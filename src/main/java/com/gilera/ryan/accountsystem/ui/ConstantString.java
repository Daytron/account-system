/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.ui;

/**
 *
 * @author Ryan Gilera
 */
enum ConstantString {
    
    MENU_SEPARATOR("\n###########################"),
    MENU_RESULT_SEPARATOR("\n-------------------------"),
    MENU("What would you like to do? \n"
            + "1. Create New Account\n"
            + "2. Deposit\n"
            + "3. Display Balance\n"
            + "4. Withdraw\n"
            + "5. Transfer Money\n"
            + "6. Pay Interest\n"
            + "7. Add Account Holder\n"
            + "8. Show all accounts Held by a customer\n"
            + "9. View Transactions\n"
            + "0. Exit\n"),
    NEW_ACCOUNTS_MENU("Choose from any of these accounts.\n"
            + "1. Current Account\n"
            + "2. Savings Account\n"
            + "3. Student Account\n"
            + "4. Business Account\n"
            + "5. SMB Account\n"
            + "6. IR Account\n"
            + "0. Cancel account creation.\n"),
    ////// ERROR MESSAGES
    ERROR_MSG_ACCOUNT_NUMBER("Error! Invalid account number entered."),
    ERROR_INVALID_NAME("Error! Invalid name entered."),
    ERROR_MSG_INVALID_OPTION("Error! Invalid option. Please try again."),
    ERROR_MSG_INVALID_MONEY("Error! Invalid amount entered."),
    ERROR_MSG_INSUFFICIENT_AMOUNT_TO_TRANSFER("Error! Insufficient funds "
            + "to make this transfer."),
    ERROR_OVERLIMIT_MAX_WITHDRAW_PART1("Error! The maximum daily withdrawal for a "),
    ERROR_OVERLIMIT_MAX_WITHDRAW_PART2(" account is "),
    ERROR_OVERLIMIT_MAX_WITHDRAW_PART3(". This transaction has been cancelled"),
    ERROR_PAY_WITH_INTEREST_EMPTY_LIST("Error! There are no accounts stored."),
    ERROR_TRANSACTION_EMPTY("Error! There is no transactions on this account."),
    
    ///// CANCEL MESSAGES
    CANCEL_NEW_ACCOUNT("The account creation has been cancelled. "),
    
    ///// CONFIRMATION MESSAGES
    CONFIRM_EXIT_QUESTION("Are you sure you want to exit? (y/n):"),
    
    ///// INSTRUCTION MESSAGES
    ENTER_OPTION("Please enter your option: "),
    ENTER_CLIENT_NAME("Enter client first and last name"),
    ENTER_ACCOUNT_NUM_DEFAULT("Enter account number: "),
    ENTER_DEPOSIT_AMOUNT("Enter amount to deposit: "),
    ENTER_TRANSFER_ACCOUNT_FROM("Enter account Number to transfer money FROM: "),
    ENTER_TRANSFER_ACCOUNT_TO("Enter account Number to transfer money TO: "),
    ENTER_TRANSFER_AMOUNT("Enter transfer amount: "),
    ENTER_WITHDRAW_AMOUNT("Enter withdraw amount: "),
    
    
    ///// SUCCESS MESSAGES
    SUCCESS_TRANSFER("Payment has been successfully transferred"),
    SUCCESS_PAY_INTEREST_MANUALLY("Interest has been paid"),
    SUCCESS_WITHDRAWAL("Account successfully withdrawn."),
    SUCESS_VIEW_TRANS_BY_CLIENT("Here are the transaction details: "),
    SUCCESS_BALANCE("The client account balance is: "),
    
    END_MESSAGE("Thank you for using automatic banking teller. "
                + "Have a good day.")
    
    ;
    
    private final String text;
    
    private ConstantString(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
    
}
