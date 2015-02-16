/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.ui;

import com.gilera.ryan.accountsystem.account.BaseAccount;
import com.gilera.ryan.accountsystem.account.BusinessAccount;
import com.gilera.ryan.accountsystem.account.CurrentAccount;
import com.gilera.ryan.accountsystem.account.IRAccount;
import com.gilera.ryan.accountsystem.account.SMBAccount;
import com.gilera.ryan.accountsystem.account.SavingsAccount;
import com.gilera.ryan.accountsystem.account.StudentAccount;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ryan Gilera
 */
public class Menu {
    static Scanner input = new Scanner(System.in); 
    ArrayList<BaseAccount> accounts = new ArrayList<BaseAccount>();     
    int acc_num;     
    int acc_number;     
    double amount; 
    String name; 
 
    public Menu() {         
        main_menu(); 
    } 
 
    public void main_menu() { 
 
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
        System.out.println("Select option: "); 
 
        int option = Integer.parseInt(input.nextLine()); 
 
        switch (option) { 
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
                 switch(option2){
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
             case 2: 
                //Write the instruction to the user 
                System.out.println("Enter account Number: ");                 
//Convert the string the user enters to an int                 
                acc_number = Integer.parseInt(input.nextLine()); 
                //Write instruction to the user 
                System.out.println("Enter deposit amount: ");                 
//Convert the string entered by the user to a double                 
                amount = Double.parseDouble(input.nextLine());                 
                for (int i = 0; i < accounts.size(); i++) { 
                    if (accounts.get(i).getAccountNum() == acc_number) 
                    {                         
                        accounts.get(i).deposit(amount);
                        accounts.get(i).addTransaction(new Date(), "Deposit", amount);
                        break; 
                    }                 
                }                 
                break; 
             case 3: 
                //Write the instruction to the user 
                System.out.println("Enter account Number: ");                 
//Convert the string the user enters to an int                 
                acc_number = Integer.parseInt(input.nextLine());                 
                for (int i = 0; i < accounts.size(); i++) { 
                    if (accounts.get(i).getAccountNum() == acc_number) 
                    {                        
                        System.out.println((accounts.get(i).get_balance()));
                        accounts.get(i).addTransaction(new Date(), "View Balance", amount);
                        break; 
                    }                 
                }                 
                break; 
             case 4: 
                //Write the instruction to the user 
                System.out.println("Enter account Number: ");                 
//Convert the string the user enters to an int                 
                acc_number = Integer.parseInt(input.nextLine()); 
                //Write instruction to the user 
                System.out.println("Enter deposit amount: ");                 
//Convert the string entered by the user to a double                 
                amount = Double.parseDouble(input.nextLine()); 
                 
                 for (int i = 0; i < accounts.size(); i++) { 
                    if (accounts.get(i).getAccountNum() == acc_number) 
                    {                         
                        if (accounts.get(i).get_acc_type() == "Current") {                             
                            if (amount <= 500) 
                            { 
                                accounts.get(i).withdraw(amount); 
                                accounts.get(i).addTransaction(new Date(), "Withdraw", amount);
                                break; 
                            } else { 
                                System.out.println("The maximum daily withdrawal for a Current account is ?500. This transaction has been cancelled");                                 break; 
                            }  
                        } else if (accounts.get(i).get_acc_type() == "Savings") 
                        {                             
                            if (amount <= 300) 
                            { 
                                accounts.get(i).withdraw(amount);     
                                accounts.get(i).addTransaction(new Date(), "Withdraw", amount);
                                break; 
                            } 
                            else 
                            { 
                                System.out.println("The maximum daily withdrawal for a Savings account is ?300. This transaction has been cancelled");                                 break; 
                            }  
                        } 
                        else if (accounts.get(i).get_acc_type() == "Business") 
                        {                             
                            if (amount <= 500) 
                            { 
                                accounts.get(i).withdraw(amount);
                                accounts.get(i).addTransaction(new Date(), "Withdraw", amount);
                                break; 
                            } 
                            else 
                            { 
                                System.out.println("The maximum daily withdrawal for a Business account is ?500. This transaction has been cancelled");                                 break; 
                            }                          
                        }                         
                        break; 
                    } 
                }                
                                 
                 break; 
             case 5:                 //Write the instruction to the user 
                System.out.println("Enter account Number to transfer money FROM:"); 
                //Convert the string the user enters to an int                 
                acc_number = Integer.parseInt(input.nextLine()); 
                //Write instruction to the user 
                System.out.println("Enter transfer amount: ");                 
//Convert the string entered by the user to a double                 
                amount = Double.parseDouble(input.nextLine());                 
                for (int i = 0; i < accounts.size(); i++) { 
                    if (accounts.get(i).getAccountNum() == acc_number) { 
                         
                        if(accounts.get(i).get_balance() >= amount) 
                        {                             
                            accounts.get(i).withdraw(amount); 
                            accounts.get(i).addTransaction(new Date(), "Transfer", amount);
                            break; 
                        } 
                        else 
                        { 
                            System.out.println("There are insufficient funds to make this transfer"); 
                            break; 
                        } 
                    } 
                } 
                 
                System.out.println("Enter account Number to transfer money TO: "); 
                //Convert the string the user enters to an int                 
                acc_number = Integer.parseInt(input.nextLine());                 
                for (int i = 0; i < accounts.size(); i++) 
                { 
                    if (accounts.get(i).getAccountNum() == acc_number) 
                    {                         
                        accounts.get(i).deposit(amount);
                        accounts.get(i).addTransaction(new Date(), "Transfer", amount);
                        break; 
                    } 
                }     
                 
               System.out.println("Payment has been successfully transferred"); 
                                 
               break;     
                   
            case 6: 
             
                for (int i = 0; i < accounts.size(); i++) {                     
                    accounts.get(i).interest_rate(); 
                } 
                System.out.println("Interest has been paid");                 
                break;                 
                 
             
            case 7: 
                System.out.println("Enter Account Number");                 
                acc_num = Integer.parseInt(input.nextLine()); 
                System.out.println("Enter Customer first and Last Name");                 
                name = input.nextLine(); 
                for (int i = 0; i < accounts.size(); i++) 
                { 
                    if (accounts.get(i).getAccountNum() == acc_number) 
                    {                         
                        accounts.get(i).AddAccHolder(name, acc_num);                         
                        break; 
                    } 
                }                   
                break;              
             case 8: 
                System.out.println("Enter Account Number");                 
                int _id = Integer.parseInt(input.nextLine());
                for (int i = 0; i < accounts.size(); i++) 
                { 
                    if (accounts.get(i).getID()== acc_number) 
                    {                         
                        System.out.println(accounts.get(i).get_acc_type()+" "+accounts.get(i).getHolderName()+" "+accounts.get(i).get_balance());                         
                         
                    } 
                }                   
                break; 
             case 9:
                 System.out.println("Enter Account Number");                 
                acc_num = Integer.parseInt(input.nextLine());
                int j=0;
                for (int i = 0; i < accounts.size(); i++) 
                { 
                    if (accounts.get(i).getAccountNum()== acc_number) 
                    {      
                        while(j<accounts.get(i).getTransactions().size())
                        {
                            System.out.println(accounts.get(i).getTransactions().get(j).getType()+" "+accounts.get(i).getTransactions().get(j).getDate()+" "+accounts.get(i).getTransactions().get(j).getAmount());
                            j++;
                        }
                         
                    } 
                }             
                 break;
        } 
 
        main_menu(); 
 
    }
}
