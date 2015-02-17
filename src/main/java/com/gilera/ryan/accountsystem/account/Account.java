/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.asset.Money;

/**
 *
 * @author Ryan Gilera
 */
public interface Account {
    public void deposit(Money amount); 
    public Money getBalance();
}
