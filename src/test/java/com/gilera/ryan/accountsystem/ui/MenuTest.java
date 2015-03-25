/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Ryan Gilera
 */
public class MenuTest {
    
    public MenuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of launch method, of class Menu.
     */
    @Test
    @Ignore
    public void testCreateAccount() {
        // Given
        Menu menu = Menu.getInstance();
        String input = "1\n1\nRyan Gilera";
        menu.launch();
        
        
    }
    
    /**
     * Test of launch method, of class Menu.
     */
    @Test
    @Ignore
    public void testLaunch() {
    }

    /**
     * Test of getInstance method, of class Menu.
     */
    @Test
    @Ignore
    public void testGetInstance() {
        System.out.println("getInstance");
        Menu expResult = null;
        Menu result = Menu.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
