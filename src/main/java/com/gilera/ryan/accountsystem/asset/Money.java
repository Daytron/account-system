/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.asset;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Ryan Gilera
 */
public final class Money {

    // Constant Strings
    private static final String CURRENCY_SYMBOL = "£";
    private static final String ZERO_STRING = "0";
    private static final String DECIMAL_POINT = ".";

    // Exception messages
    private static final String CONSTRUCTOR_EXCEPTION_MSG
            = "Value is almost at its limit (9,223,372,036,854,775,807). "
            + "Please consider lowering the value.";
    private static final String PRODUCT_EXCEPTION_MSG
            = "The sum value is too big!";
    private static final String QUOTIENT_EXCEPTION_MSG
            = "The quotient is too small"
            + " to return as Money. Result: ";

    private final long pounds;
    private final long pence;
    private final Sign sign;

    public Money() {
        this(Sign.Positive, 0, 0);
    }

    public Money(long pounds) {
        this(Sign.Positive, pounds, 0);
    }

    public Money(Sign sign, long pounds) {
        this(sign, pounds, 0);
    }

    public Money(Sign sign, long pounds, long pence) {

        if (Long.toString(pounds).length()
                == Long.toString(Long.MAX_VALUE).length()) {
            throw new IllegalArgumentException(CONSTRUCTOR_EXCEPTION_MSG);
        }

        // Normalise pence that is over 100
        // Any extras will add up to the pounds
        if (pence > 99) {
            pounds += pence / 100;
            pence %= 100;
        }

        // Prevent negative zero money
        // For simplicity zero is considered positive
        // but when represented in String format,
        // it is unsign.
        if (pounds == 0 && pence == 0) {
            this.sign = Sign.Positive;
        } else {
            this.sign = sign;
        }

        this.pounds = pounds;
        this.pence = pence;
    }

    /**
     * <p>
     * A Money object factory. Returns a new instance of @see
     * com.github.daytron.accountsystem.Money .
     *
     * Acceptable inputs:
     * <ul>
     * <li>"+1.2" = 1.2
     * <li>"-2.49" = 2.49
     * <li>"-2." = -2.00
     * <li>"38." = 38.00
     * <li>"456" = 456.00
     * <li>"23.6192342" = 23.61
     * </ul>
     *
     *
     *
     * <p>
     * IllegalArgumentException conditions:
     * <ul>
     * <li>Can only accept 999 Billion max Does
     * <li>not accept multiple decimal points Does not accept point at the
     * beginning
     * <li>without a digit preceding it.
     * </ul>
     *
     * <p>
     * Otherwise, it returns <code>Null</code>.
     *
     * @param valueString
     * @return Money
     */
    public static Money valueOf(String valueString) {
        if (valueString == null || valueString.isEmpty()) {
            return null;
        }

        String[] parsedData = Money.parseValue(valueString, true);
        Sign sign;
        String poundString, penceString;

        // Parse sign
        if ("1".equals(parsedData[0])) {
            sign = Sign.Positive;
        } else {
            sign = Sign.Negative;
        }

        poundString = parsedData[1];
        penceString = parsedData[2];

        return new Money(sign,
                Long.parseLong(poundString), Long.parseLong(penceString));

    }

    public long getPounds() {
        return pounds;
    }

    public long getPence() {
        return pence;
    }

    public Sign getSign() {
        return sign;
    }

    public static String[] parseValue(String valueString, boolean isPenceOnly) {
        // First element = sign 1 for positive and 0 for negative
        // Second element for pounds
        // Third element for pence
        final String[] parsedData = new String[3];

        valueString = valueString.trim();

        /*
         Regex conditions:
         ^   = Beginning of the line
         -?  = Optional negative symbol
         \d{1,} = 1 or more digits
         \.? = An optional dot (escaped, because in regex, . is a special character)
         \d* = 0 or more digits (the decimal part);
         $   = End of the line.
         */
        if (valueString.matches("^[-+]?\\d{1,12}\\.?\\d*$")) {

            /*
             This is more forgiving with ending dot input.
             If the last character given is a dot, it actually accepts it as 
             [digit]., resulting to "£ [digit/s].00"
             Chops off dot at the end.
             */
            if (valueString.charAt(valueString.length() - 1) == '.') {
                valueString = valueString.substring(0, valueString.length() - 1);
            }

            if (valueString.contains(".")) {

                if (isPenceOnly) {
                    // Chops off smaller decimal places
                    // Ex. 1.2284 becomes 1.22
                    if (valueString.substring(valueString.indexOf(".") + 1)
                            .length() > 2) {
                        int indexOfBeginningCharToCut = valueString.indexOf(".") + 3;
                        valueString = valueString.substring(0, indexOfBeginningCharToCut);
                    }
                }

                // Filters single digit decimal number as two digits
                // Ex. 1.2 is 1.20 and NOT 1.02
                if (valueString.indexOf(".") == valueString.length() - 2) {
                    valueString += "0";
                }
            }

            // Determines sign and then removes it
            // 1 for positive and 0 for negative
            if (valueString.contains("-")) {
                parsedData[0] = "0";

                // Removes sign
                valueString = valueString.substring(1);
            } else if (valueString.contains("+")) {
                parsedData[0] = "1";

                // Removes sign
                valueString = valueString.substring(1);
            } else {
                parsedData[0] = "1";
            }

            // Extract values
            if (valueString.contains(".")) {
                parsedData[1] = valueString.substring(0, valueString.indexOf("."));
                parsedData[2] = valueString.substring(valueString.indexOf(".") + 1);
            } else {
                parsedData[1] = valueString;
                parsedData[2] = "00";
            }

            return parsedData;

        } else if (valueString.matches("^[-+]?\\d{1,}\\.?\\d*$")) {
            /*
             Throws an illegal argument exception if the whole numbers exceeds
             999 Billion (999,999,999,999.00).
             Long data type can hold upto 9,223,372,036,854,775,807 (19 digits).
             Limits input up to 12 whole numbers (999 Billion max), excluding 
             decimal digits if given.
             */
            throw new IllegalArgumentException("Invalid input. "
                    + "Value is too big! Can only accept up to "
                    + "999,999,999,999.99");
        } else if ((valueString.indexOf('.', valueString.indexOf('.') + 1) != -1)) {
            /*
             Throws an illegal argument exeption if multiple occurences of dot 
             is detected in the argument input.
             */
            throw new IllegalArgumentException("Invalid number entry. "
                    + "Multiple dots detected.");
        } else if (valueString.charAt(0) == '.' || valueString.contains("+.")
                || valueString.contains("-.")) {
            /*
             Throws an illegal argument exception if a dot is detected in
             the first character or any sign followed by a dot.
             */
            throw new IllegalArgumentException("Invalid number entry. "
                    + "Invalid use of decimal point.");
        } else if (valueString.matches("^[-+]{2,}\\d{1,}\\.?\\d*$")) {
            /*
             Throws an illegal argument exception if multiple characters of the 
             sign symbols are detected.
             */
            throw new IllegalArgumentException("Invalid number entry. "
                    + "Invalid use of sign symbols.");
        } else {
            // Throws a standard illegal argument exception
            throw new IllegalArgumentException("Invalid number entry.");
        }
    }

    public Money plus(Money money) {
        if (money == null) {
            return null;
        }

        long newPounds;
        long newPence;
        Sign newSign;

        long thisPounds = getPounds();
        long thisPence = getPence();
        long thatPounds = money.getPounds();
        long thatPence = money.getPence();
        Sign thisSign = getSign();
        Sign thatSign = money.getSign();

        if ((thisSign == Sign.Positive && thatSign == Sign.Positive)
                || (thisSign == Sign.Negative && thatSign == Sign.Negative)) {

            newPounds = thisPounds + thatPounds;
            newPence = thisPence + thatPence;

            // Normalise pence that is over 100
            if (newPence > 99) {
                newPounds += newPence / 100;
                newPence %= 100;
            }

            // Determine sign
            // Follow the sign of the highest number which is this Money
            if (getSign() == Sign.Positive) {
                newSign = Sign.Positive;
            } else {
                newSign = Sign.Negative;
            }

        } else {

            // Determine sign
            // Reverse sign and swap values if thisPounds is greater than thatPounds
            // or they are equal whole numbers but thatPence is greater than 
            // thisPence. Otherwise retain sign;
            if (thisPounds < thatPounds
                    || (thisPounds == thatPounds && thisPence < thatPence)) {
                // Reverse sign
                newSign = thatSign;

                // Swap pounds
                long tempPoundsHolder = thisPounds;
                thisPounds = thatPounds;
                thatPounds = tempPoundsHolder;

                // Swap pence
                long tempPenceHolder = thisPence;
                thisPence = thatPence;
                thatPence = tempPenceHolder;
            } else if (thisPounds == thatPounds && thisPence == thatPence) {
                // this condition result to zero value hence positive
                // For simplicity sake, zero is assume positive
                // Note: In the toString method, zero value money is filtered to 
                // show no sign at all
                newSign = Sign.Positive;
            } else {
                newSign = thisSign;
            }

            // Calculate value
            if (thisPence >= thatPence) {
                newPence = thisPence - thatPence;
                newPounds = thisPounds - thatPounds;
            } else {
                // this money's pence is less than the given pence
                // First borrow 1 from thisPounds
                thisPounds -= 1;
                // Then add extra 100 pence to newPence
                newPence = (100 + thisPence) - thatPence;
                newPounds = thisPounds - thatPounds;
            }

        }

        return new Money(newSign, newPounds, newPence);
    }

    public Money minus(Money money) {
        if (money == null) {
            return null;
        }

        return plus(new Money(money.getSign().oppositeOf(),
                money.getPounds(), money.getPence()));
    }

    public Money multipliedBy(String valueString) {
        if (valueString == null || valueString.isEmpty()) {
            return null;
        }
        
        String[] parsedData = Money.parseValue(valueString, false);
        
        Sign thatSign, newSign;
        String thatPoundString, thatPenceString;
        
        long newPounds, newPence;

        // Parse sign for money 2
        if ("1".equals(parsedData[0])) {
            thatSign = Sign.Positive;
        } else {
            thatSign = Sign.Negative;
        }

        thatPoundString = parsedData[1];
        thatPenceString = parsedData[2];

        // Determine sign
        if (getSign() == thatSign) {
            newSign = Sign.Positive;
        } else {
            newSign = Sign.Negative;
        }
        
        // Convert thisMoney to String literal
        // Ex. 12.34 = "1234"
        String tempThisMoney = Long.toString(getPounds());
        // Add an extra 0 if this pence is less than 10
        if (getPence() < 10) {
            tempThisMoney += ZERO_STRING;
        }
        
        tempThisMoney += Long.toString(getPence());
        
        // Get that money string equivalent in whole numbers
        String tempThatMoney = thatPoundString + thatPenceString;
        
        // Calculate the final decimal places
        // 2 is from this object which is a money object with default 2 decimal
        // places plus the money pence
        int newDecimalPlace = 2 + thatPenceString.length();
        
        // Convert to long datatype
        Long tempLongThisMoney = Long.valueOf(tempThisMoney);
        Long tempLongThatMoney = Long.valueOf(tempThatMoney);
        
        
        // Do the multiplication
        Long product = tempLongThisMoney * tempLongThatMoney;

        if (product == 0) {
            return new Money();
        }
        
        // Convert it back to String to enable to extract each number position
        String tempProduct = Long.toString(product);

        // Extract each character (number) to a value stored in an array 
        String[] parsedNumbers = extractEachNumber(tempProduct);
        String tempPounds = "";
        String tempPence = "";

        // Extract the whole number (pounds) and the decimal (pence)
        if (parsedNumbers.length > newDecimalPlace) {
            for (int i = 0; i < parsedNumbers.length; i++) {
                if (i < (parsedNumbers.length - newDecimalPlace)) {
                    tempPounds += parsedNumbers[i];
                } else {
                    // Extracts the first two decimal places
                    if (i < (Math.abs(parsedNumbers.length-newDecimalPlace+2))) {
                        tempPence += parsedNumbers[i];
                    } else {
                        // Ignore smaller decimal places
                        break;
                    }
                }
            }
            
            // Convert each back to long type
            newPounds = Long.valueOf(tempPounds);
            newPence = Long.valueOf(tempPence);
            
        } else if (parsedNumbers.length == newDecimalPlace) {
            // Scenario where parseNumbers is exactly the same length as the
            // decimal place, which means that all of those numbers is pence digits
            newPounds = 0;

            for (int j = 0; j < 2; j++) {
                tempPence += parsedNumbers[j];
            }

            newPence = Long.valueOf(tempPence);
        } else if ((Math.abs(parsedNumbers.length - newDecimalPlace) == 1)) {
            // Scenario where the length of parseNumbers is 3 and the decimal place is 4
            // then the first element value of parseNumbers is a single pence digit
            // Ex. parsed = 678 but decimal place is 4 resulting to 0.0678, thus
            // only "6" is extracted as single digit.
            newPounds = 0;
            newPence = Long.valueOf(parsedNumbers[0]);
        } else {
            // Results to zero value if the product is too small
            throw new ArithmeticException(QUOTIENT_EXCEPTION_MSG);
        }

        return new Money(newSign, newPounds, newPence);

    }

    public Money multipliedBy(Money money) {
        if (money == null) {
            return null;
        }
        String moneyStr = money.toString().replace(",", "");

        return multipliedBy(moneyStr);
    }

    public Money dividedBy(Money money) {
        // Division by zero is undefined
        if (money == null || money.isZero()) {
            return null;
        }

        Sign newSign;
        long newPounds;
        long newPence;

        // Determine sign
        if (getSign() == money.getSign()) {
            newSign = Sign.Positive;
        } else {
            newSign = Sign.Negative;
        }

        // If divisor is 1, quotient is the dividend
        if (money.getPounds() == 1 && money.getPence() == 0) {
            return new Money(newSign, getPounds(), getPence());
        }

        // If dividend is zero, quotient is zero
        if (isZero()) {
            return new Money();
        }

        // Convert thisMoney to String literal
        // Ex. 12.34 = "1234"
        // 12 = "1200"
        String tempThisMoney = Long.toString(getPounds());
        // Add an extra 0 if this pence is less than 10
        if (getPence() < 10 && getPence() > 0) {
            tempThisMoney += ZERO_STRING;
            tempThisMoney += Long.toString(getPence());
        } else if (getPence() == 0) {
            tempThisMoney += ZERO_STRING + ZERO_STRING;
        } else {
            tempThisMoney += Long.toString(getPence());
        }

        // Convert thisMoney to String literal
        // Ex. 12.34 = "1234"
        // 12 = "1200"
        String tempThatMoney = Long.toString(money.getPounds());
        // Add an extra 0 if this pence is less than 10
        if (money.getPence() < 10 && money.getPence() > 0) {
            tempThatMoney += ZERO_STRING;
            tempThatMoney += Long.toString(money.getPence());
        } else if (money.getPence() == 0) {
            tempThatMoney += ZERO_STRING + ZERO_STRING;
        } else {
            tempThatMoney += Long.toString(money.getPence());
        }

        // Convert to long datatype
        Long tempLongThisMoney = Long.valueOf(tempThisMoney);
        Long tempLongThatMoney = Long.valueOf(tempThatMoney);

        // Do the division operation
        // By using the same number of tens or significant digit, 
        // it assures the same result if rendered with decimal places.
        // It is converted to double to enable to extract decimal places
        // Ex. 12.5 / 1.2 is the same as 1250 / 120
        double quotient = tempLongThisMoney / (tempLongThatMoney * 1.0);

        // Convert it back to String to enable to extract each number position
        String tempQuotient = Double.toString(quotient);

        // Extract each character (number) to a value stored in an array 
        String[] parsedNumbers = extractEachNumber(tempQuotient);

        // Prepare String pounds and pence equivalent
        String tempPounds = "";
        String tempPence = "";

        // Extract the whole number (pounds) and the decimal (pence)
        if (tempQuotient.contains(".")) {
            final int indexAtPoint = tempQuotient.indexOf(".");
            for (int i = 0; i < parsedNumbers.length; i++) {
                if (i < indexAtPoint) {
                    tempPounds += parsedNumbers[i];
                } else if (i > indexAtPoint) {

                    // Extracts the first two decimal places
                    if (i < (indexAtPoint + 3)) {
                        tempPence += parsedNumbers[i];
                    } else {
                        // Ignore smaller decimal places
                        break;
                    }
                }
            }
        } else {
            tempPounds = tempQuotient;
            tempPence = "0";
        }

        // Convert each back to long type
        // Sometimes the result is too small, ex. 2.OE-4
        // Smallest value before going got E is 0.000 (value < E-4)
        if (tempPence.contains("0E")) {
            // Note: This must filter first before checking if newPence and
            // newPounds are zeros, to prevent any conversion exception later
            throw new ArithmeticException(QUOTIENT_EXCEPTION_MSG + quotient);
        } else {

            if (Long.valueOf(tempPounds) == 0
                    && Long.valueOf(tempPence) == 0) {
                // if the value is too small ex. 0.004 which parsed pounds and
                // pence as zeros
                throw new ArithmeticException(QUOTIENT_EXCEPTION_MSG + quotient);
            } else if (tempPence.length() == 1) {
                // if decimal value is single digit,
                // apply approriate value
                // Ex. 0.4 = 0.40
                tempPence += ZERO_STRING;
            }

            newPounds = Long.valueOf(tempPounds);
            newPence = Long.valueOf(tempPence);
        }

        return new Money(newSign, newPounds, newPence);
    }

    private String[] extractEachNumber(String numberStr) {
        if (numberStr == null) {
            return null;
        }

        final int len = numberStr.length();
        String[] listOfNumbers = new String[len];

        for (int i = 0; i < len; i++) {
            listOfNumbers[i] = Character.toString(numberStr.charAt(i));
        }

        return listOfNumbers;
    }

    public boolean isLessThan(Money money) {
        if (money == null) {
            return false;
        } else if (getSign() == Sign.Negative && money.getSign() == Sign.Positive) {
            return true;
        } else if (getSign() == Sign.Positive && money.getSign() == Sign.Negative) {
            return false;
        } else if (getSign() == Sign.Negative && money.getSign() == Sign.Negative) {
            if (getPounds() > money.getPounds()) {
                return true;
            } else if (getPounds() == money.getPounds()) {
                return getPence() > money.getPence();
            } else {
                return false;
            }
        } else {
            // All positive values
            if (getPounds() < money.getPounds()) {
                return true;
            } else if (getPounds() == money.getPounds()) {
                return getPence() < money.getPence();
            } else {
                return false;
            }
        }

    }

    public boolean isGreaterThan(Money money) {
        if (money == null) {
            return false;
        }

        if (getSign() == Sign.Negative && money.getSign() == Sign.Positive) {
            return false;
        } else if (getSign() == Sign.Positive && money.getSign() == Sign.Negative) {
            return true;
        } else if (getSign() == Sign.Negative && money.getSign() == Sign.Negative) {
            if (getPounds() < money.getPounds()) {
                return true;
            } else if (getPounds() == money.getPounds()) {
                return getPence() < money.getPence();
            } else {
                return false;
            }
        } else {
            // All positive values
            if (getPounds() > money.getPounds()) {
                return true;
            } else if (getPounds() == money.getPounds()) {
                return getPence() > money.getPence();
            } else {
                return false;
            }
        }

    }

    public boolean isEqualTo(Money money) {
        if (money == null) {
            return false;
        }

        return getSign() == money.getSign()
                && getPounds() == money.getPounds()
                && getPence() == money.getPence();
    }

    @Override
    public String toString() {
        // Apply sign
        String signText;
        if (getPounds() == 0 && getPence() == 0) {
            signText = "";
        } else if (getSign() == Sign.Positive) {
            signText = "";
        } else {
            signText = getSign().getText();
        }

        // Reformat value to proper value format
        // Ex. 1267 becomes 1,267
        String poundsStr = NumberFormat.getInstance().format(getPounds());

        // Determine single digit pence and reformat its display
        String penceStr;
        if (getPence() < 10 && getPence() > 0) {
            penceStr = ZERO_STRING;
            penceStr += getPence();
        } else if (getPence() == 0) {
            penceStr = ZERO_STRING + ZERO_STRING;
        } else {
            penceStr = Long.toString(getPence());
        }

        return signText + poundsStr + DECIMAL_POINT + penceStr;
    }

    public boolean isPositiveOrZero() {
        return getSign() == Sign.Positive;
    }

    public boolean isPositive() {
        return getSign() == Sign.Positive && (getPounds() > 0 || getPence() > 0);
    }

    public boolean isNegative() {
        return getSign() == Sign.Negative;
    }

    public boolean isZero() {
        return getPounds() == 0 && getPence() == 0;
    }
}
