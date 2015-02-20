/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.task;

import com.gilera.ryan.accountsystem.account.BaseAccount;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author Ryan Gilera
 */
public class ScheduledTask extends TimerTask {
    private boolean isAccountsSet;
    private List<BaseAccount> accounts;
    
    // 3 minutes interval
    // converts to milliseconds
    private static final int INTERVAL_TIME = 3 * 60 * 1000;

    private ScheduledTask() {
        this.isAccountsSet = false;
    }

    public static int getINTERVAL_TIME() {
        return INTERVAL_TIME;
    }
    
    public void setAccounts(List<BaseAccount> accounts) {
        // Can only set accounts once!
        if (!isAccountsSet) {
            this.accounts = accounts;
            this.isAccountsSet = true;
        }
    }

    @Override
    public void run() {
        // Do automatic interest
        for (BaseAccount account : accounts) {
            account.payWithInterest();
            account.applyOverdraftPenaltyIfPossible();
        }
    }

    public static ScheduledTask getInstance() {
        return ScheduledTaskHolder.INSTANCE;
    }
    
    private static class ScheduledTaskHolder {
        private static final ScheduledTask INSTANCE = new ScheduledTask();
    }
}
