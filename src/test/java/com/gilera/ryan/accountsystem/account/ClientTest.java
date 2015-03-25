/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.account;

import com.gilera.ryan.accountsystem.utility.StringUtil;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan Gilera
 */
public class ClientTest {
    
    public ClientTest() {
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
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        // Given
        String givenName = "Perrin Aybayra";
        givenName = StringUtil.formatClientName(givenName);
        Client aClient = new Client(givenName);
        
        // When
        String resultName = aClient.getName();
        
        // Then
        assertEquals(givenName, resultName);
    }

    /**
     * Test of getId method, of class Client.
     */
    @Test
    public void testGetId() {
        // Given
        Client aClient = new Client("Sarah");
        
        // When
        UUID resultUuid = aClient.getId();
        
        // Then
        assertNotNull(resultUuid);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        // Given
        String givenName = "Regina";
        Client clientA = new Client(givenName);
        Client clientB = new Client(givenName);
        boolean expResult = false;
        
        // When
        boolean result = clientA.equals(clientB);
        
        // Then
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        // Giveb
        String givenName = "Snow";
        Client aClient = new Client(givenName);
        
        // When
        String resultStr = aClient.toString();
        
        // Then
        assertNotNull(resultStr);
    }
    
}
