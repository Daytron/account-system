/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.utility;

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
public class MoneyTest {

    public MoneyTest() {
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
     * Test of getPounds method, of class Money.
     */
    @Test
    public void testGetPounds() {
        // Given:
        Money instance = new Money(Sign.Positive, 12, 5);
        long expResult = 12;

        // When:
        long result = instance.getPounds();

        // Then:
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPence method, of class Money.
     */
    @Test
    public void testGetPence() {
        // Given:
        Money instance = new Money(Sign.Positive, 12, 5);
        long expResult = 5;

        // When:
        long result = instance.getPence();

        // Then:
        assertEquals(expResult, result);
    }

    /**
     * Test of getSign method, of class Money.
     */
    @Test
    public void testGetSign() {
        // Given:
        Money instance = new Money(Sign.Positive, 12, 5);
        Sign expResult = Sign.Positive;

        // When:
        Sign result = instance.getSign();

        // Then:
        assertEquals(expResult, result);
    }

    /**
     * Test of plus method, of class Money.
     */
    @Test
    public void testPlus() {
        // CASE 1: (+)(+) & money1 > money2
        // Given:
        Money money1 = new Money(Sign.Positive, 12, 5);
        Money money2 = new Money(Sign.Positive, 3, 15);

        Money expResult = new Money(Sign.Positive, 15, 20);

        // When:
        Money result = money1.plus(money2);

        // Then:
        assertEquals("Case 1: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 1: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 1: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 2: (+)(+) & money1 < money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Positive, 12, 5);

        expResult = new Money(Sign.Positive, 15, 20);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 2: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 2: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 2: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 3: (+)(+) & money1 = money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Positive, 3, 15);

        expResult = new Money(Sign.Positive, 6, 30);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 3: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 3: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 3: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 4: (+)(-) & money1 > money2
        // Given:
        money1 = new Money(Sign.Positive, 12, 5);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Positive, 8, 90);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 4: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 4: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 4: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 5: (+)(-) & money1 < money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Negative, 12, 5);

        expResult = new Money(Sign.Negative, 8, 90);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 5: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 5: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 5: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 6: (+)(-) & money1 = money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Positive, 0, 0);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 6: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 6: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 6: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 7: (-)(+) & money1 > money2
        // Given:
        money1 = new Money(Sign.Negative, 12, 5);
        money2 = new Money(Sign.Positive, 3, 15);

        expResult = new Money(Sign.Negative, 8, 90);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 7: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 7: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 7: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 8: (-)(+) & money1 < money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Positive, 12, 5);

        expResult = new Money(Sign.Positive, 8, 90);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 8: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 8: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 8: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 9: (-)(+) & money1 = money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Positive, 3, 15);

        expResult = new Money(Sign.Positive, 0, 0);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 9: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 9: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 9: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 10: (-)(-) & money1 > money2
        // Given:
        money1 = new Money(Sign.Negative, 12, 5);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Negative, 15, 20);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 10: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 10: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 10: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 11: (-)(-) & money1 < money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Negative, 12, 5);

        expResult = new Money(Sign.Negative, 15, 20);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 11: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 11: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 11: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 12: (-)(-) & money1 = money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Negative, 6, 30);

        // When:
        result = money1.plus(money2);

        // Then:
        assertEquals("Case 12: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 12: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 12: Invalid pence value.",
                expResult.getPence(), result.getPence());
    }

    /**
     * Test of minus method, of class Money.
     */
    @Test
    public void testMinus() {

        // CASE 1: (+)(+) & money1 > money2
        // Given:
        Money money1 = new Money(Sign.Positive, 12, 5);
        Money money2 = new Money(Sign.Positive, 3, 15);

        Money expResult = new Money(Sign.Positive, 8, 90);

        // When:
        Money result = money1.minus(money2);

        // Then:
        assertEquals("Case 1: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 1: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 1: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 2: (+)(+) & money1 < money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Positive, 12, 5);

        expResult = new Money(Sign.Negative, 8, 90);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 2: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 2: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 2: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 3: (+)(+) & money1 = money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Positive, 3, 15);

        expResult = new Money(Sign.Positive, 0, 0);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 3: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 3: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 3: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 4: (+)(-) & money1 > money2
        // Given:
        money1 = new Money(Sign.Positive, 12, 5);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Positive, 15, 20);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 4: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 4: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 4: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 5: (+)(-) & money1 < money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Negative, 12, 5);

        expResult = new Money(Sign.Positive, 15, 20);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 5: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 5: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 5: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 6: (+)(-) & money1 = money2
        // Given:
        money1 = new Money(Sign.Positive, 3, 15);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Positive, 6, 30);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 6: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 6: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 6: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 7: (-)(+) & money1 > money2
        // Given:
        money1 = new Money(Sign.Negative, 12, 5);
        money2 = new Money(Sign.Positive, 3, 15);

        expResult = new Money(Sign.Negative, 15, 20);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 7: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 7: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 7: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 8: (-)(+) & money1 < money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Positive, 12, 5);

        expResult = new Money(Sign.Negative, 15, 20);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 8: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 8: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 8: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 9: (-)(+) & money1 = money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Positive, 3, 15);

        expResult = new Money(Sign.Negative, 6, 30);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 9: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 9: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 9: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 10: (-)(-) & money1 > money2
        // Given:
        money1 = new Money(Sign.Negative, 12, 5);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Negative, 8, 90);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 10: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 10: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 10: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 11: (-)(-) & money1 < money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Negative, 12, 5);

        expResult = new Money(Sign.Positive, 8, 90);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 11: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 11: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 11: Invalid pence value.",
                expResult.getPence(), result.getPence());

        // CASE 12: (-)(-) & money1 = money2
        // Given:
        money1 = new Money(Sign.Negative, 3, 15);
        money2 = new Money(Sign.Negative, 3, 15);

        expResult = new Money(Sign.Positive, 0, 0);

        // When:
        result = money1.minus(money2);

        // Then:
        assertEquals("Case 12: Invalid sign.",
                expResult.getSign(), result.getSign());
        assertEquals("Case 12: Invalid pounds value.",
                expResult.getPounds(), result.getPounds());
        assertEquals("Case 12: Invalid pence value.",
                expResult.getPence(), result.getPence());

    }

    /**
     * Test of isLessThan method, of class Money.
     */
    @Test
    public void testIsLessThan() {

        // Case 1: (+)(+) & absolute (money1 < money2)
        // Given:
        Money money1 = new Money(Sign.Positive, 6, 50);
        Money money2 = new Money(Sign.Positive, 6, 83);

        boolean expResult = true;

        // When:
        boolean result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 1:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 2: (+)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Positive, 183, 299);

        expResult = false;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 2: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 3: (+)(+) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = false;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 3: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 4: (+)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Positive, 6, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = false;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 4:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 5: (+)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = false;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 5: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 6: (+)(-) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = false;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 6: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 7: (-)(+) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Positive, 15, 299);

        expResult = true;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 7:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 8: (-)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 5);

        expResult = true;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 8: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 9: (-)(+) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = true;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 9: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 10: (-)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Negative, 15, 69);

        expResult = false;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 10:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 11: (-)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 69);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = true;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 11: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 12: (-)(-) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = false;

        // When:
        result = money1.isLessThan(money2);

        // Then:
        assertEquals("Case 12: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of isGreaterThan method, of class Money.
     */
    @Test
    public void testIsGreaterThan() {

        // Case 1: (+)(+) & absolute (money1 < money2)
        // Given:
        Money money1 = new Money(Sign.Positive, 6, 50);
        Money money2 = new Money(Sign.Positive, 6, 83);

        boolean expResult = false;

        // When:
        boolean result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 1:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 2: (+)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Positive, 183, 299);

        expResult = true;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 2: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 3: (+)(+) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = false;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 3: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 4: (+)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Positive, 6, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = true;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 4:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 5: (+)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = true;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 5: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 6: (+)(-) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = true;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 6: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 7: (-)(+) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Positive, 15, 299);

        expResult = false;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 7:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 8: (-)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 5);

        expResult = false;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 8: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 9: (-)(+) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = false;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 9: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 10: (-)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Negative, 15, 69);

        expResult = true;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 10:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 11: (-)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 15);

        expResult = false;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 11: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 12: (-)(-) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = false;

        // When:
        result = money1.isGreaterThan(money2);

        // Then:
        assertEquals("Case 12: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of toString method, of class Money.
     */
    @Test
    public void testToString() {
        // Case 1: Value > 0
        // Given:
        Money instance = new Money(Sign.Positive, 12569, 5);
        String expResult = "£ 12,569.05";

        // When:
        String result = instance.toString();

        // Then:
        assertEquals("Case 1: Value > 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 2: Value = 0
        // Given:
        instance = new Money();
        expResult = "£ 0.00";

        // When:
        result = instance.toString();

        // Then:
        assertEquals("Case 2: Value = 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 3: Value < 0
        // Given:
        instance = new Money(Sign.Negative, 1246580, 89);
        expResult = "£ -1,246,580.89";

        // When:
        result = instance.toString();

        // Then:
        assertEquals("Case 3: Value < 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of equals method, of class Money.
     */
    @Test
    public void testIsEqualTo() {
        
        // Case 1: (+)(+) & absolute (money1 < money2)
        // Given:
        Money money1 = new Money(Sign.Positive, 6, 50);
        Money money2 = new Money(Sign.Positive, 6, 83);

        boolean expResult = false;

        // When:
        boolean result = money1.equals(money2);

        // Then:
        assertEquals("Case 1:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 2: (+)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Positive, 183, 299);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 2: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 3: (+)(+) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = true;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 3: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 4: (+)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Positive, 6, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 4:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 5: (+)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 5: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 6: (+)(-) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 6: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 7: (-)(+) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Positive, 15, 299);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 7:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 8: (-)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 5);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 8: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 9: (-)(+) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 9: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 10: (-)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Negative, 15, 299);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 10:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 11: (-)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 05);

        expResult = false;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 11: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);

        // Case 12: (-)(-) & absolute (money1 = money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = true;

        // When:
        result = money1.equals(money2);

        // Then:
        assertEquals("Case 12: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of multipliedBy method, of class Money.
     */
    @Test
    public void testMultipliedBy() {
        
        // Case 1: (+)(+)
        // Given:
        Money money1 = new Money(Sign.Positive, 6, 50);
        Money money2 = new Money(Sign.Positive, 6, 83);

        Money expResult = new Money(Sign.Positive, 44, 39);

        // When:
        Money result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 1:  Where both are positive. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 2: (-)(-) 
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = new Money(Sign.Positive, 551181, 36);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 2: Where both are negative. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 3: money1 is zero & money2 is non-zero
        // Given:
        money1 = new Money();
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = new Money(Sign.Positive, 0, 0);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 3: Where Money1 = 0 & Money2 != 0. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 4: money1 is non-zero & money2 is zero
        // Given:
        money1 = new Money(Sign.Positive, 6, 50);
        money2 = new Money();

        expResult = new Money(Sign.Positive, 0, 0);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 4:  Where Money1 != 0 & Money2 = 0. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 5: (+)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = new Money(Sign.Negative, 551181, 36);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 5: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 6: (+)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Positive, 1, 0);
        money2 = new Money(Sign.Negative, 2963, 50);

        expResult = new Money(Sign.Negative, 2963, 50);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 6: Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 7: (-)(+) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Positive, 15, 299);

        expResult = new Money(Sign.Negative, 278, 84);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 7:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 8: (-)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 6, 5);

        expResult = new Money(Sign.Negative, 17929, 17);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 8: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 9: Both zero
        // Given:
        money1 = new Money(Sign.Negative, 0, 0);
        money2 = new Money(Sign.Positive, 0, 0);

        expResult = new Money();

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 9: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 10: Less than 1
        // Given:
        money1 = new Money(Sign.Positive, 0, 50);
        money2 = new Money(Sign.Negative, 0, 3);

        expResult = new Money(Sign.Negative, 0, 1);

        // When:
        result = money1.multipliedBy(money2);

        // Then:
        assertEquals("Case 10:  Where both are less than 1. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());
    }

    /**
     * Test of valueOf method, of class Money.
     */
    @Test
    public void testValueOf() {
        // Case 1: Whole numbers only
        // Given:
        String moneyString = "32";
        Money expResult = new Money(32);
        
        // When:
        Money result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 1: Whole numbers only. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 2: Whole numbers with single decimal digit
        // Given:
        moneyString = "32.2";
        expResult = new Money(Sign.Positive, 32, 20);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 2: Whole numbers with single decimal digit. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 3: Whole numbers with two decimal digits
        // Given:
        moneyString = "32.21";
        expResult = new Money(Sign.Positive, 32, 21);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 3: Whole numbers with two decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 4: Whole numbers with three decimal digits
        // Given:
        moneyString = "32.219";
        expResult = new Money(Sign.Positive, 32, 21);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 4: Whole numbers with three decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 5: Whole numbers with dot at the end
        // Given:
        moneyString = "32.";
        expResult = new Money(Sign.Positive, 32, 0);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 5: Whole numbers with dot at the end. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 6: Negative whole numbers with three decimal digits
        // Given:
        moneyString = "-32.219";
        expResult = new Money(Sign.Negative, 32, 21);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 6: Negative whole numbers with three decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 7: Negative whole numbers with two decimal digits
        // Given:
        moneyString = "-32.21";
        expResult = new Money(Sign.Negative, 32, 21);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 7: Negative whole numbers with two decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 8: Negative whole numbers with single decimal digits
        // Given:
        moneyString = "-32.2";
        expResult = new Money(Sign.Negative, 32, 20);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 8: Negative whole numbers with single decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 9: Negative whole numbers only
        // Given:
        moneyString = "-32";
        expResult = new Money(Sign.Negative, 32, 0);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 9: Negative whole numbers only. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 10: Negative whole numbers with dot at the end
        // Given:
        moneyString = "-32.";
        expResult = new Money(Sign.Negative, 32, 0);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 10: Negative whole numbers with dot at the end. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 11: Positive whole numbers with three decimal digits
        // Given:
        moneyString = "+32.219";
        expResult = new Money(Sign.Positive, 32, 21);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 11: Positive whole numbers with three decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 12: Positive whole numbers with two decimal digits
        // Given:
        moneyString = "+32.21";
        expResult = new Money(Sign.Positive, 32, 21);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 12: Positive whole numbers with two decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 13: Positive whole numbers with single decimal digits
        // Given:
        moneyString = "+32.2";
        expResult = new Money(Sign.Positive, 32, 20);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 13: Positive whole numbers with single decimal digits. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 14: Positive whole numbers only
        // Given:
        moneyString = "+32";
        expResult = new Money(Sign.Positive, 32, 0);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 14: Positive whole numbers only. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 15: Positive whole numbers with dot at the end
        // Given:
        moneyString = "+32.";
        expResult = new Money(Sign.Positive, 32, 0);
        
        // When:
        result = Money.valueOf(moneyString);
        
        // Then:
        assertEquals("Case 15: Positive whole numbers with dot at the end. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult.toString(), 
                result.toString());
        
        // Case 16: Empty value
        // Given:
        expResult = null;
        
        // When:
        result = Money.valueOf("");
        
        // Then:
        assertEquals("Case 16: Empty value. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 17: Null value
        // Given:
        expResult = null;
        
        // When:
        result = Money.valueOf(null);
        
        // Then:
        assertEquals("Case 17: Null value. "
                + "Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of isPositiveOrZero method, of class Money.
     */
    @Test
    public void testIsPositiveOrZero() {
        // Case 1: value > 0
        // Given:
        Money money = new Money(32);
        boolean expResult = true;
        
        // When:
        boolean result = money.isPositiveOrZero();
        
        // Then:
        assertEquals("Case 1: Value > 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 2: value = 0
        // Given:
        money = new Money();
        expResult = true;
        
        // When:
        result = money.isPositiveOrZero();
        
        // Then:
        assertEquals("Case 2: Value = 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 3: value < 0
        // Given:
        money = new Money(Sign.Negative, 12, 80);
        expResult = false;
        
        // When:
        result = money.isPositiveOrZero();
        
        // Then:
        assertEquals("Case 3: Value < 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of isPositive method, of class Money.
     */
    @Test
    public void testIsPositive() {
        // Case 1: value > 0
        // Given:
        Money money = new Money(32);
        boolean expResult = true;
        
        // When:
        boolean result = money.isPositive();
        
        // Then:
        assertEquals("Case 1: Value > 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 2: value = 0
        // Given:
        money = new Money();
        expResult = false;
        
        // When:
        result = money.isPositive();
        
        // Then:
        assertEquals("Case 2: Value = 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 3: value < 0
        // Given:
        money = new Money(Sign.Negative, 12, 80);
        expResult = false;
        
        // When:
        result = money.isPositive();
        
        // Then:
        assertEquals("Case 3: Value < 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of isNegative method, of class Money.
     */
    @Test
    public void testIsNegative() {
        // Case 1: value > 0
        // Given:
        Money money = new Money(32);
        boolean expResult = false;
        
        // When:
        boolean result = money.isNegative();
        
        // Then:
        assertEquals("Case 1: Value > 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 2: value = 0
        // Given:
        money = new Money();
        expResult = false;
        
        // When:
        result = money.isNegative();
        
        // Then:
        assertEquals("Case 2: Value = 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 3: value < 0
        // Given:
        money = new Money(Sign.Negative, 12, 80);
        expResult = true;
        
        // When:
        result = money.isNegative();
        
        // Then:
        assertEquals("Case 3: Value < 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of isZero method, of class Money.
     */
    @Test
    public void testIsZero() {
        // Case 1: value > 0
        // Given:
        Money money = new Money(32);
        boolean expResult = false;
        
        // When:
        boolean result = money.isZero();
        
        // Then:
        assertEquals("Case 1: Value > 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 2: value = 0
        // Given:
        money = new Money();
        expResult = true;
        
        // When:
        result = money.isZero();
        
        // Then:
        assertEquals("Case 2: Value = 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
        
        // Case 3: value < 0
        // Given:
        money = new Money(Sign.Negative, 12, 80);
        expResult = false;
        
        // When:
        result = money.isPositiveOrZero();
        
        // Then:
        assertEquals("Case 3: Value < 0. Should be " + expResult + 
                ", but instead it's " + result, expResult, result);
    }

    /**
     * Test of dividedBy method, of class Money.
     */
    @Test
    public void testDividedBy() {
        
        // Case 1: (+)(+)
        // Given:
        Money money1 = new Money(Sign.Positive, 863456, 50);
        Money money2 = new Money(Sign.Positive, 6, 83);

        Money expResult = new Money(Sign.Positive, 126421, 15);

        // When:
        Money result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 1:  Where both are positive. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 2: (-)(-) 
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = new Money(Sign.Positive, 15, 93);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 2: Where both are negative. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 3: money1 is zero & money2 is non-zero
        // Given:
        money1 = new Money();
        money2 = new Money(Sign.Positive, 2963, 50);

        expResult = new Money(Sign.Positive, 0, 0);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 3: Where Money1 = 0 & Money2 != 0. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 4: money1 is non-zero & money2 is 1
        // Given:
        money1 = new Money(Sign.Positive, 6, 50);
        money2 = Money.valueOf("-1.0");

        expResult = new Money(Sign.Negative, 6, 50);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 4: Where money1 is non-zero & money2 is 1. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 5: (+)(-) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Positive, 2963, 50);
        money2 = new Money(Sign.Negative, 183, 299);

        expResult = new Money(Sign.Negative, 15, 93);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 5: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 6: (+)(-) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Positive, 1, 0);
        money2 = new Money(Sign.Negative, 2, 50);

        expResult = new Money(Sign.Negative, 0, 40);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 6: Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 7: (-)(+) & absolute (money1 < money2)
        // Given:
        money1 = new Money(Sign.Negative, 15, 50);
        money2 = new Money(Sign.Positive, 15, 299);

        expResult = new Money(Sign.Negative, 0, 86);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 7:  Where absolute (Money1 < Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 8: (-)(+) & absolute (money1 > money2)
        // Given:
        money1 = new Money(Sign.Negative, 2963, 50);
        money2 = new Money(Sign.Positive, 6, 50);

        expResult = new Money(Sign.Negative, 455, 92);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 8: Where absolute (Money1 > Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());

        // Case 9: Both zero
        // Given:
        money1 = new Money(Sign.Negative, 0, 0);
        money2 = new Money(Sign.Positive, 0, 0);

        expResult = null;

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 9: Where absolute (Money1 = Money2). Should be "
                + expResult + ", but instead it's " + result, 
                expResult, result);

        // Case 10: Less than 1
        // Given:
        money1 = new Money(Sign.Positive, 0, 50);
        money2 = new Money(Sign.Negative, 0, 3);

        expResult = new Money(Sign.Negative, 16, 66);

        // When:
        result = money1.dividedBy(money2);

        // Then:
        assertEquals("Case 10:  Where both are less than 1. Should be "
                + expResult + ", but instead it's " + result, 
                expResult.toString(), result.toString());
    }

}
