package ui;

import java.util.*;

/**
 * 
 */
public class Menu {

    /**
     * 
     */
    public Menu() {
    }

    /**
     * 
     */
    private Map<String, Integer> optionsForMainMenu;

    /**
     * 
     */
    private Map<String, Integer> optionsForNewAccountMenu;

    /**
     * 
     */
    private Map<String, Integer> optionsForViewTransactionsMenu;

    /**
     * 
     */
    private Map<String, Integer> optionsForTransactionTypesMenu;

    /**
     * 
     */
    private static int TOTAL_MAIN_MENU_OPTIONS = 10;

    /**
     * 
     */
    private static int TOTAL_ACCOUNT_OPTIONS = 10;

    /**
     * 
     */
    public static int TOTAL_VIEW_TRANSACTIONS_OPTIONS = 5;

    /**
     * 
     */
    private static int TOTAL_TRANSACTION_TYPE_OPTIONS = 7;

    /**
     * 
     */
    private static long INITIAL_ACCOUNT_NUMBER = 1000000;

    /**
     * 
     */
    private Scanner input;

    /**
     * 
     */
    private List<BaseAccount> listOfAccounts;

    /**
     * 
     */
    private long accountNumberCounter;

    /**
     * 
     */
    private ScheduledTask scheduledTask;







    /**
     * @return
     */
    public void launch() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void pressEnterKeyToContinue() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void displayMenuSeparator() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void displayMenuResultSeparator() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void createNewAccount() {
        // TODO implement here
        return null;
    }

    /**
     * @param account 
     * @return
     */
    private void displayResultForNewAccountCreation(BaseAccount account) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void deposit() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void withdraw() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void transferMoney() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void payWithInterest() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void viewTransactions() {
        // TODO implement here
        return null;
    }

    /**
     * @param accountToView 
     * @return
     */
    private void displayResultForViewTransactionsSingle(BaseAccount accountToView) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void addAccountHolder() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void showAccountHeldByAClient() {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    private Client retrieveClient(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void viewBalance() {
        // TODO implement here
        return null;
    }

    /**
     * @param listOfOptions 
     * @return
     */
    private int processInputForMenuOptions(Map<String, Integer> listOfOptions) {
        // TODO implement here
        return 0;
    }

    /**
     * @param messageToUser 
     * @return
     */
    private BaseAccount processInputForAccountNumber(String messageToUser) {
        // TODO implement here
        return null;
    }

    /**
     * @param messageToUser 
     * @return
     */
    private Money processInputForMoney(String messageToUser) {
        // TODO implement here
        return null;
    }

    /**
     * @param messageToUser 
     * @return
     */
    private Date processInputForDate(String messageToUser) {
        // TODO implement here
        return null;
    }

    /**
     * @param instructionMsg 
     * @return
     */
    private String processInputForClientName(String instructionMsg) {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public static Menu getInstance() {
        // TODO implement here
        return null;
    }

}