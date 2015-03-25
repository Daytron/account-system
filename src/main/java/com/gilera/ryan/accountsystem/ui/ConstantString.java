package com.gilera.ryan.accountsystem.ui;

/**
 * An enumeration type to hold String constants for Menu class.
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
    MENU_SEPARATOR_MAIN("="),
    MENU_SEPARATOR_RESULT("-"),
    
    ///// MENUS
    MENU_MAIN("MAIN MENU\n"
            + "What would you like to do? \n"
            + " [1] Create New Account\n"
            + " [2] Deposit\n"
            + " [3] Display Balance\n"
            + " [4] Withdraw\n"
            + " [5] Transfer Money\n"
            + " [6] Add Account Holder\n"
            + " [7] Show all accounts held by a customer\n"
            + " [8] View Transactions\n"
            + " [0] Exit\n"),
    MENU_NEW_ACCOUNTS("Choose from any of these accounts.\n"
            + " [1] Current Account\n"
            + " [2] Savings Account\n"
            + " [3] Student Account\n"
            + " [4] Business Account\n"
            + " [5] SMB Account\n"
            + " [6] IR Account\n"
            + " [7] Cash Investment Account\n"
            + " [8] Child Account\n"
            + " [9] International Account\n"
            + " [0] Cancel operation\n"),
    MENU_VIEW_TRANSACTIONS("Filter results by any of these options: \n"
            + " [1] View transactions of all accounts\n"
            + " [2] View transactions for one account\n"
            + " [3] View transactions by certain date range for one account\n"
            + " [4] View transactions by type for one account\n"
            + " [5] View transactions by client\n"
            + " [0] Cancel operation\n"),
    MENU_VIEW_TRANSACTION_TYPES("Filter results by transaction type:\n"
            + " [1] Deposit\n"
            + " [2] Balance\n"
            + " [3] Withdraw\n"
            + " [4] Funds Transfer\n"
            + " [5] Paid Interest\n"
            + " [6] Paid Overdraft Penalty\n"
            + " [0] Cancel operation\n"),
    
    ////// ERROR MESSAGES
    ERROR("Error! "),
    ERROR_INVALID_ACCOUNT_NUMBER("Error! Invalid account number entered."),
    ERROR_INVALID_NAME("Error! Invalid name entered."),
    ERROR_INVALID_OPTION("Error! Invalid option. Please try again.\n"),
    ERROR_INVALID_MONEY("Error! Invalid amount entered."),
    ERROR_INSUFFICIENT_AMOUNT_TO_TRANSFER("Error! Insufficient funds "
            + "to make this transfer."),
    ERROR_OVERLIMIT_MAX_WITHDRAW_PART1("Error! The maximum daily withdrawal for a "),
    ERROR_OVERLIMIT_MAX_WITHDRAW_PART2(" account is "),
    ERROR_OVERLIMIT_OVERDRAFT_WITHDRAWAL("Error! This account cannot exceed its "
            + "overdraft limit."),
    ERROR_PAY_WITH_INTEREST_EMPTY_LIST("Error! There are no accounts stored."),
    ERROR_ALL_TRANSACTIONS_EMPTY("Error! There are no transactions stored yet."),
    ERROR_TRANSACTION_EMPTY("Empty. No transactions found on this account."),
    ERROR_INVALID_DATE_INPUT("Error! Invalid date input. Please follow "
            + "\"dd-mm-yyyy\" format."),
    ERROR_REVERSED_DATE_RANGE("Error! 2nd date is before the 1st date. Range "
            + "must be from 1st date to 2nd date."),
    ERROR_BOTH_DATES_SAME("Error! Both dates are the same. "),
    ERROR_NO_CLIENT_FOUND("No client record goes by that name."),
    ERROR_OPERATION_CANCELLED("This operation has been cancelled."),
    ERROR_SAME_ACCOUNT_TRANSFER("Error! Same bank account.\n"
            + "Transfer of funds on the same account is not allowed."),
    ERROR_AMOUNT_INPUT_ZERO("Error! Amount entered is zero."),
    
    ///// CONFIRMATION MESSAGES
    CONFIRM_EXIT_QUESTION("Are you sure you want to exit? \n"
            + "(Enter \"y\" to exit, other keys to cancel): "),
    
    ///// INSTRUCTION MESSAGES
    ENTER_OPTION("Enter your selection [0-"),
    ENTER_CLIENT_NAME("Enter client first and last name: "),
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
    SUCCESS_TRANSFER("Fund has been successfully transferred."),
    SUCCESS_PAY_INTEREST_MANUALLY("Interest has been paid."),
    SUCCESS_DEPOSIT("Account successfully deposited."),
    SUCCESS_WITHDRAWAL("Account successfully withdrawn."),
    SUCESS_VIEW_ACCOUNT_BY_NAME("Account(s) found:"),
    SUCCESS_BALANCE("The client account balance is: "),
    SUCCESS_CLIENT_FOUND("A previous client record found."),
    SUCCESS_ACCOUNT_CREATED("An account has been created."),
    
    CREATE_NEW_CLIENT("Creating new client record instead...\n"
            + "Client record created.\n"),
    CREATE_NEW_ACCOUNT_MSG("Creating new account..."),
    
    FAIL_NO_TRANSACTIONS_FOUND_BY_TYPE("Empty. No records found for "
            + "this transaction type."),
    FAIL_NO_TRANSACTIONS_FOUND_BY_DATES("Empty. No transaction records found "
            + "within these dates: "),
    
    
    END_MESSAGE("Thank you for using automatic banking teller.\n"
                + "Have a good day.");
    
    private final String text;
    
    private ConstantString(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
    
}
