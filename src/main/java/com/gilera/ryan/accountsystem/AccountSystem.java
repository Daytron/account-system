package com.gilera.ryan.accountsystem;

import com.gilera.ryan.accountsystem.ui.Menu;

/**
 * The main class as the entry point of the program.
 * @author Ryan Gilera
 */
public class AccountSystem {
    public static void main (String args[])
        {
            Menu appMenu = Menu.getInstance();
            appMenu.launch();
        }
}
