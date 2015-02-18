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
    
    WELCOME_TITLE(
"\n\n\n  " +
  "ÛÛÛÛÛÛÛÛ      ÛÛÛÛÛ     ÛÛ            ÛÛÛÛÛÛÛÛÛÛÛ                       ÛÛÛÛÛ     \n" +
" ÛÛÛ°°°°ÛÛÛ   ÛÛÛ°°°ÛÛÛ  ÛÛÛ           °°ÛÛÛ°°°°°ÛÛÛ                     °°ÛÛÛ      \n" +
"°ÛÛÛ   °ÛÛÛ  ÛÛÛ   °°ÛÛÛ°°°   ÛÛÛÛÛ     °ÛÛÛ    °ÛÛÛ  ÛÛÛÛÛÛ   ÛÛÛÛÛÛÛÛ   °ÛÛÛ ÛÛÛÛÛ\n" +
"°°ÛÛÛÛÛÛÛÛ  °ÛÛÛ    °ÛÛÛ     ÛÛÛ°°      °ÛÛÛÛÛÛÛÛÛÛ  °°°°°ÛÛÛ °°ÛÛÛ°°ÛÛÛ  °ÛÛÛ°°ÛÛÛ \n" +
" ÛÛÛ°°°°ÛÛÛ °ÛÛÛ    °ÛÛÛ    °°ÛÛÛÛÛ     °ÛÛÛ°°°°°ÛÛÛ  ÛÛÛÛÛÛÛ  °ÛÛÛ °ÛÛÛ  °ÛÛÛÛÛÛ°  \n" +
"°ÛÛÛ   °ÛÛÛ °°ÛÛÛ   ÛÛÛ      °°°°ÛÛÛ    °ÛÛÛ    °ÛÛÛ ÛÛÛ°°ÛÛÛ  °ÛÛÛ °ÛÛÛ  °ÛÛÛ°°ÛÛÛ \n" +
"°°ÛÛÛÛÛÛÛÛ   °°°ÛÛÛÛÛ°       ÛÛÛÛÛÛ     ÛÛÛÛÛÛÛÛÛÛÛ °°ÛÛÛÛÛÛÛÛ ÛÛÛÛ ÛÛÛÛÛ ÛÛÛÛ ÛÛÛÛÛ\n" +
" °°°°°°°°      °°°°°°       °°°°°°     °°°°°°°°°°°   °°°°°°°° °°°° °°°°° °°°° °°°°° \n"
            + "\n\n"
            + "   ÛÛÛÛÛÛÛÛÛ                                                     ÛÛÛÛÛ        ÛÛÛÛÛÛÛÛÛ                      ÛÛÛÛÛ                            \n" +
"  ÛÛÛ°°°°°ÛÛÛ                                                   °°ÛÛÛ        ÛÛÛ°°°°°ÛÛÛ                    °°ÛÛÛ                             \n" +
" °ÛÛÛ    °ÛÛÛ   ÛÛÛÛÛÛ   ÛÛÛÛÛÛ   ÛÛÛÛÛÛ  ÛÛÛÛÛ ÛÛÛÛ ÛÛÛÛÛÛÛÛ   ÛÛÛÛÛÛÛ     °ÛÛÛ    °°°  ÛÛÛÛÛ ÛÛÛÛ  ÛÛÛÛÛ  ÛÛÛÛÛÛÛ    ÛÛÛÛÛÛ  ÛÛÛÛÛÛÛÛÛÛÛÛÛ  \n" +
" °ÛÛÛÛÛÛÛÛÛÛÛ  ÛÛÛ°°ÛÛÛ ÛÛÛ°°ÛÛÛ ÛÛÛ°°ÛÛÛ°°ÛÛÛ °ÛÛÛ °°ÛÛÛ°°ÛÛÛ °°°ÛÛÛ°      °°ÛÛÛÛÛÛÛÛÛ °°ÛÛÛ °ÛÛÛ  ÛÛÛ°°  °°°ÛÛÛ°    ÛÛÛ°°ÛÛÛ°°ÛÛÛ°°ÛÛÛ°°ÛÛÛ \n" +
" °ÛÛÛ°°°°°ÛÛÛ °ÛÛÛ °°° °ÛÛÛ °°° °ÛÛÛ °ÛÛÛ °ÛÛÛ °ÛÛÛ  °ÛÛÛ °ÛÛÛ   °ÛÛÛ        °°°°°°°°ÛÛÛ °ÛÛÛ °ÛÛÛ °°ÛÛÛÛÛ   °ÛÛÛ    °ÛÛÛÛÛÛÛ  °ÛÛÛ °ÛÛÛ °ÛÛÛ \n" +
" °ÛÛÛ    °ÛÛÛ °ÛÛÛ  ÛÛÛ°ÛÛÛ  ÛÛÛ°ÛÛÛ °ÛÛÛ °ÛÛÛ °ÛÛÛ  °ÛÛÛ °ÛÛÛ   °ÛÛÛ ÛÛÛ    ÛÛÛ    °ÛÛÛ °ÛÛÛ °ÛÛÛ  °°°°ÛÛÛ  °ÛÛÛ ÛÛÛ°ÛÛÛ°°°   °ÛÛÛ °ÛÛÛ °ÛÛÛ \n" +
" ÛÛÛÛÛ   ÛÛÛÛÛ°°ÛÛÛÛÛÛ °°ÛÛÛÛÛÛ °°ÛÛÛÛÛÛ  °°ÛÛÛÛÛÛÛÛ ÛÛÛÛ ÛÛÛÛÛ  °°ÛÛÛÛÛ    °°ÛÛÛÛÛÛÛÛÛ  °°ÛÛÛÛÛÛÛ  ÛÛÛÛÛÛ   °°ÛÛÛÛÛ °°ÛÛÛÛÛÛ  ÛÛÛÛÛ°ÛÛÛ ÛÛÛÛÛ\n" +
"°°°°°   °°°°°  °°°°°°   °°°°°°   °°°°°°    °°°°°°°° °°°° °°°°°    °°°°°      °°°°°°°°°    °°°°°ÛÛÛ °°°°°°     °°°°°   °°°°°°  °°°°° °°° °°°°° \n" +
"                                                                                          ÛÛÛ °ÛÛÛ                                            \n" +
"                                                                                         °°ÛÛÛÛÛÛ                                             \n" +
"                                                                                          °°°°°°                                              \n"
            + "Version 1.0\n"
            + "By: Ryan Gilera"),
    
    WELCOME_MESSAGE("Welcome to the world's advanced account teller system"),
    
    ///// MENU SEPRATORS
    MENU_SEPARATOR_MAIN("\n###########################"),
    MENU_SEPARATOR_RESULT("\n-------------------------"),
    
    ///// MENUS
    MENU_MAIN("MAIN MENU\n"
            + "What would you like to do? \n"
            + "1. Create New Account\n"
            + "2. Deposit\n"
            + "3. Display Balance\n"
            + "4. Withdraw\n"
            + "5. Transfer Money\n"
            + "6. Pay Interest\n"
            + "7. Add Account Holder\n"
            + "8. Show all accounts held by a customer\n"
            + "9. View Transactions\n"
            + "0. Exit\n"),
    MENU_NEW_ACCOUNTS("Choose from any of these accounts.\n"
            + "1. Current Account\n"
            + "2. Savings Account\n"
            + "3. Student Account\n"
            + "4. Business Account\n"
            + "5. SMB Account\n"
            + "6. IR Account\n"
            + "7. Cash Investment Account\n"
            + "8. Child Account\n"
            + "9. International Account\n"
            + "0. Cancel operation\n"),
    MENU_VIEW_TRANSACTIONS("Filter results by any of these of options: \n"
            + "1. View all transactions of all accounts\n"
            + "2. View all transactions for one account\n"
            + "3. View all transactions by certain date range for one account\n"
            + "4. View all transactions by type for one account\n"
            + "0. Cancel operation\n"),
    MENU_VIEW_TRANSACTION_TYPES("Filter results by transaction type:\n"
            + "1. Deposit\n"
            + "2. Balance\n"
            + "3. Withdraw\n"
            + "4. Funds Transfer\n"
            + "5. Paid Interest\n"
            + "6. Paid Overdraft Penalty\n"
            + "0. Cancel operation\n"),
    
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
    ERROR_OVERLIMIT_OVERDRAFT_WITHDRAWAL("Error! This account cannot exceed its "
            + "overdraft limit. Cancelling transaction..."),
    ERROR_PAY_WITH_INTEREST_EMPTY_LIST("Error! There are no accounts stored."),
    ERROR_ALL_TRANSACTIONS_EMPTY("Error! There are no transactions stored yet."),
    ERROR_TRANSACTION_EMPTY("Error! There is no transactions on this account."),
    ERROR_INVALID_DATE_INPUT("Error!. Invalid date input. Please follow "
            + "\"dd-mm-yyyy\" format. Cancelling operation.."),
    ERROR_REVERSED_DATE_RANGE("Error! 2nd date is before the 1st date. Range "
            + "must be from 1st date to 2nd date. Cancelling operation..."),
    ERROR_BOTH_DATES_SAME("Error! Both dates are the same. Cancelling operation..."),
    
    ///// CANCEL MESSAGES
    CANCEL_NEW_ACCOUNT("The account creation has been cancelled. "),
    CANCEL_VIEW_TRANSACTIONS("The view transaction operation has been cancelled."),
    
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
    ENTER_TRANSACTION_DATE_TO("Enter the first date (format: \"dd-mm-yyyy\"):"),
    ENTER_TRANSACTION_DATE_FROM("Enter the second date (format: \"dd-mm-yyyy\"):"),
    
    ///// SUCCESS MESSAGES
    SUCCESS_VIEW_TRANSACTIONS_SINGLE("Transaction logs for: "),
    SUCCESS_VIEW_TRANSACTIONS_ALL("Transaction logs for all accounts: "),
    SUCCESS_TRANSFER("Payment has been successfully transferred"),
    SUCCESS_PAY_INTEREST_MANUALLY("Interest has been paid"),
    SUCCESS_DEPOSIT("Account successfully deposited."),
    SUCCESS_WITHDRAWAL("Account successfully withdrawn."),
    SUCESS_VIEW_ACCOUNT("Account information successfully retrieved."),
    SUCCESS_BALANCE("The client account balance is: "),
    
    FAIL_NO_TRANSACTIONS_FOUND_BY_TYPE("Empty. No transaction records found."),
    FAIL_NO_TRANSACTIONS_FOUND_BY_DATES("Empty. No transaction records found "
            + "within these dates: "),
    
    END_MESSAGE("Thank you for using automatic banking teller. "
                + "Have a good day.\n")
    
    ;
    
    private final String text;
    
    private ConstantString(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
    
}
