package com.gilera.ryan.accountsystem.task;

import com.gilera.ryan.accountsystem.account.BaseAccount;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/**
 * Class responsible for all automated scheduled tasks.
 * This includes adding interest and applying overdraft
 * penalty fee every 3 minutes. 
 * 
 * This class is a subclass of TimerTask for scheduling
 * task in a separate thread. 
 * 
 * In addition this class uses Singleton pattern to limit
 * all object instantiations to only one.
 * 
 * @author Ryan Gilera
 */
public final class ScheduledTask extends TimerTask {
    private boolean isAccountsSet;
    private List<BaseAccount> accounts;
    
    // 3 minutes interval
    // converts to milliseconds
    private static final int INTERVAL_TIME = 3 * 60 * 1000;

    /**
     * Private constructor for Singleton pattern.
     * Default value of isAccountSet is set to false.
     */
    private ScheduledTask() {
        this.isAccountsSet = false;
    }

    public static int getINTERVAL_TIME() {
        return INTERVAL_TIME;
    }
    
    /**
     * Receives and sets the list of accounts to process. 
     * This action is only allowed once.
     * 
     * @param accounts List of accounts as List object
     */
    public void setAccounts(List<BaseAccount> accounts) {
        // Can only set accounts once!
        // Prevents overriding the variable later on
        if (!isAccountsSet) {
            this.accounts = accounts;
            this.isAccountsSet = true;
        }
    }

    /**
     * Override's Runnable class run method inherited by TimerTask.
     * This method process all interest and penalty payments. 
     */
    @Override
    public void run() {
        // Thread safe synchronised automatic interest and penaly payments 
        synchronized (accounts) {
            Iterator<BaseAccount> iterator = accounts.iterator();
            
            while (iterator.hasNext()) {
                BaseAccount account = iterator.next();
                account.payWithInterest();
                account.applyOverdraftPenaltyIfPossible();
            }
        }
    }

    /**
     * Public static class to retrieve the one and only instance of this class.
     * 
     * @return The ScheduledTask object
     */
    public static ScheduledTask getInstance() {
        return ScheduledTaskHolder.INSTANCE;
    }
    
    /**
     * A private inner static class to hold the ScheduledTask object.
     */
    private static class ScheduledTaskHolder {
        private static final ScheduledTask INSTANCE = new ScheduledTask();
    }
}
