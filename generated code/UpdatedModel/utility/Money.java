package utility;

import java.util.*;

/**
 * 
 */
public class Money {

    /**
     * 
     */
    public Money() {
    }

    /**
     * 
     */
    private static String CURRENCY_SYMBOL = "£";

    /**
     * 
     */
    private static String ZERO_STRING = "0";

    /**
     * 
     */
    private static String DECIMAL_POINT = ".";

    /**
     * 
     */
    private static String CONSTRUCTOR_EXCEPTION_MSG = "Value is almost at its limit (9,223,372,036,854,775,807). Please consider lowering the value.";

    /**
     * 
     */
    private static String PRODUCT_EXCEPTION_MSG = "The sum value is too big!";

    /**
     * 
     */
    private static String QUOTIENT_EXCEPTION_MSG = "The quotient is too small to return as Money.  Result: ";

    /**
     * 
     */
    private long pounds;

    /**
     * 
     */
    private long pence;

    /**
     * 
     */
    private Sign sign;





    /**
     * @param valueString 
     * @return
     */
    public static Money valueOf(String valueString) {
        // TODO implement here
        return null;
    }

    /**
     * @param valueString 
     * @param isPenceOnly 
     * @return
     */
    public static String[] parseValue(String valueString, boolean isPenceOnly) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public long getPounds() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public long getPence() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public Sign getSign() {
        // TODO implement here
        return null;
    }

    /**
     * @param money 
     * @return
     */
    public Money plus(Money money) {
        // TODO implement here
        return null;
    }

    /**
     * @param money 
     * @return
     */
    public Money minus(Money money) {
        // TODO implement here
        return null;
    }

    /**
     * @param valueString 
     * @return
     */
    public Money multipliedBy(String valueString) {
        // TODO implement here
        return null;
    }

    /**
     * @param money 
     * @return
     */
    public Money multipliedBy(Money money) {
        // TODO implement here
        return null;
    }

    /**
     * @param money 
     * @return
     */
    public Money dividedBy(Money money) {
        // TODO implement here
        return null;
    }

    /**
     * @param numberStr 
     * @return
     */
    private String[] extractEachNumber(String numberStr) {
        // TODO implement here
        return null;
    }

    /**
     * @param money 
     * @return
     */
    public boolean isLessThan(Money money) {
        // TODO implement here
        return false;
    }

    /**
     * @param money 
     * @return
     */
    public boolean isGreaterThan(Money money) {
        // TODO implement here
        return false;
    }

    /**
     * @param money 
     * @return
     */
    public boolean isEqualTo(Money money) {
        // TODO implement here
        return false;
    }

    /**
     * @param money 
     * @return
     */
    public boolean isLessThanOrEqualTo(Money money) {
        // TODO implement here
        return false;
    }

    /**
     * @param money 
     * @return
     */
    public boolean isGreaterThanOrEqualTo(Money money) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isPositiveOrZero() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isPositive() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isNegative() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isZero() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public String toString() {
        // TODO implement here
        return "";
    }

}