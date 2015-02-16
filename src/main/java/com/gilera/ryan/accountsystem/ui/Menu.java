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
import com.gilera.ryan.accountsystem.log.Transaction;
import com.gilera.ryan.accountsystem.log.TransactionType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ryan Gilera
 */
public class Menu {

    private final Scanner input;
    private final ArrayList<BaseAccount> accounts;
    private int acc_num;
    private int acc_number;
    private double amount;
    private String name;

    public Menu() {
        this.input = new Scanner(System.in);
        this.accounts = new ArrayList<>();
    }

    public void launch() {
        boolean isFinish = false;

        while (!isFinish) {
            System.out.println("Press Esc to enter menu");

            System.out.println("1. Create Current Account\r\n");
            System.out.println("2. Deposit\r\n");
            System.out.println("3. Display Balance\r\n");
            System.out.println("4. Withdraw\r\n");
            System.out.println("5. Transfer Money\r\n");
            System.out.println("6. Pay Interest\r\n");
            System.out.println("7. Add Account Holder\r\n");
            System.out.println("8. Show all accounts Held by a customer\r\n");
            System.out.println("9. View Transactions\r\n");
            System.out.println("0. Exit\r\n");
            System.out.println("Select option: ");

            int option = Integer.parseInt(input.nextLine());

            switch (option) {
                // Create new account
                case 1:
                    System.out.println("1. Current Account\r\n");
                    System.out.println("2. Savings Account\r\n");
                    System.out.println("3. Student Account\r\n");
                    System.out.println("4. Business Account\r\n");
                    System.out.println("5. SMB Account\r\n");
                    System.out.println("6. IR Account\r\n");
                    int option2 = Integer.parseInt(input.nextLine());
                    System.out.println("Enter Customer first and Last Name");
                    name = input.nextLine();
                    System.out.println("Enter Customer ID");
                    int id = Integer.parseInt(input.nextLine());
                    acc_num++;

                    switch (option2) {
                        case 1:
                            accounts.add(new CurrentAccount(name, acc_num, id));
                            break;
                        case 2:
                            accounts.add(new SavingsAccount(name, acc_num, id));
                            break;
                        case 3:
                            accounts.add(new StudentAccount(name, acc_num, id));
                            break;
                        case 4:
                            accounts.add(new BusinessAccount(name, acc_num, id));
                            break;
                        case 5:
                            accounts.add(new SMBAccount(name, acc_num, id));
                            break;
                        case 6:
                            accounts.add(new IRAccount(name, acc_num, id));
                            break;
                    }

                    break;
                // Deposit    
                case 2:
                    //Write the instruction to the user 
                    System.out.println("Enter account Number: ");
                    //Convert the string the user enters to an int                 
                    acc_number = Integer.parseInt(input.nextLine());
                    //Write instruction to the user 
                    System.out.println("Enter deposit amount: ");
                    //Convert the string entered by the user to a double                 
                    amount = Double.parseDouble(input.nextLine());

                    for (BaseAccount account : accounts) {
                        if (account.getAccountNum() == acc_number) {
                            account.deposit(amount);
                            account.addTransaction(new Date(),
                                    TransactionType.DEPOSIT, amount);
                            break;
                        }
                    }
                    break;
                // View balance
                case 3:
                    //Write the instruction to the user 
                    System.out.println("Enter account Number: ");
                    //Convert the string the user enters to an int                 
                    acc_number = Integer.parseInt(input.nextLine());

                    for (BaseAccount account : accounts) {
                        if (account.getAccountNum() == acc_number) {
                            System.out.println(account.getBalance());
                            account.addTransaction(new Date(), 
                                    TransactionType.VIEW_BALANCE, amount);
                            break;
                        }
                    }
                    break;
                // Withdraw
                case 4:
                    //Write the instruction to the user 
                    System.out.println("Enter account Number: ");
                    //Convert the string the user enters to an int                 
                    acc_number = Integer.parseInt(input.nextLine());
                    //Write instruction to the user 
                    System.out.println("Enter withdraw amount: ");
                    //Convert the string entered by the user to a double                 
                    amount = Double.parseDouble(input.nextLine());

                    for (BaseAccount account : accounts) {
                        if (account.getAccountNum() == acc_number) {
                            if (account.getAccountType() == AccountType.CURRENT) {
                                if (amount <= 500) {
                                    account.withdraw(amount);
                                    account.addTransaction(new Date(),
                                            TransactionType.WITHDRAW, amount);
                                    break;
                                } else {
                                    System.out.println("The maximum daily withdrawal for a Current account is ?500. This transaction has been cancelled");
                                    break;
                                }
                            } else if (account.getAccountType() == AccountType.SAVINGS) {
                                if (amount <= 300) {
                                    account.withdraw(amount);
                                    account.addTransaction(new Date(),
                                            TransactionType.WITHDRAW, amount);
                                    break;
                                } else {
                                    System.out.println("The maximum daily withdrawal for a Savings account is ?300. This transaction has been cancelled");
                                    break;
                                }
                            } else if (account.getAccountType() == AccountType.BUSINESS) {
                                if (amount <= 500) {
                                    account.withdraw(amount);
                                    account.addTransaction(new Date(),
                                            TransactionType.WITHDRAW, amount);
                                    break;
                                } else {
                                    System.out.println("The maximum daily withdrawal for a Business account is ?500. This transaction has been cancelled");
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    break;

                // Transfer
                case 5:
                    //Write the instruction to the user 
                    System.out.println("Enter account Number to transfer money FROM:");
                    //Convert the string the user enters to an int                 
                    acc_number = Integer.parseInt(input.nextLine());
                    //Write instruction to the user 
                    System.out.println("Enter transfer amount: ");
                    //Convert the string entered by the user to a double                 
                    amount = Double.parseDouble(input.nextLine());

                    for (BaseAccount account : accounts) {
                        if (account.getAccountNum() == acc_number) {
                            if (account.getBalance() >= amount) {
                                account.withdraw(amount);
                                account.addTransaction(new Date(),
                                        TransactionType.TRANSFER, amount);
                                break;
                            } else {
                                System.out.println("There are insufficient funds to make this transfer");
                                break;
                            }
                        }
                    }

                    System.out.println("Enter account Number to transfer money TO: ");
                    //Convert the string the user enters to an int                 
                    acc_number = Integer.parseInt(input.nextLine());

                    for (BaseAccount account : accounts) {
                        if (account.getAccountNum() == acc_number) {
                            account.deposit(amount);
                            account.addTransaction(new Date(),
                                    TransactionType.TRANSFER, amount);
                            break;
                        }
                    }

                    System.out.println("Payment has been successfully transferred");

                    break;

                // Pay interest
                case 6:

                    for (BaseAccount account : accounts) {
                        account.payWithInterest();
                    }
                    System.out.println("Interest has been paid");
                    break;

                // Add account holder
                case 7:
                    System.out.println("Enter Account Number");
                    acc_num = Integer.parseInt(input.nextLine());
                    System.out.println("Enter Customer first and Last Name");
                    name = input.nextLine();

                    for (BaseAccount account : accounts) {
                        if (account.getAccountNum() == acc_number) {
                            account.AddAccHolder(name, acc_num);
                            break;
                        }
                    }
                    break;

                // Show all accounts Held by a customer
                case 8:
                    System.out.println("Enter Account Number");
                    int _id = Integer.parseInt(input.nextLine());

                    for (BaseAccount account : accounts) {
                        if (account.getID() == acc_number) {
                            System.out.println(account.getAccountType().getText()
                                    + " " + account.getHolderName()
                                    + " " + account.getBalance());
                        }
                    }
                    break;

                // View transactions
                case 9:
                    System.out.println("Enter Account Number");
                    acc_num = Integer.parseInt(input.nextLine());

                    for (BaseAccount account : accounts) {
                        if (account.getAccountNum() == acc_number) {
                            for (Transaction aTransaction : account.getTransactions()) {
                                System.out.println(
                                        aTransaction.getTransactionType().getText()
                                        + " " + aTransaction.getDate()
                                        + " " + aTransaction.getAmount());
                            }
                            // Leave the for loop
                            // There is noo need to search for other accounts
                            break;
                        }
                    }
                    break;

                // Exit
                case 0:
                    System.out.println("Are you sure you want to exit? (y/n)");
                    String response = input.nextLine();
                    if (response.equalsIgnoreCase("y")) {
                        isFinish = true;
                        break;
                    }

                default:
                    System.out.println("Unrecognised command. Please try again. ");
            }

        }

        System.out.println("Thank you for using automatic banking teller. "
                + "Have a good day.");

    }
}
