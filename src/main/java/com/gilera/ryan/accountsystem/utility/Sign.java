package com.gilera.ryan.accountsystem.utility;

/**
 * An enumeration type for sign values of Money object.
 * 
 * @author Ryan Gilera
 */
public enum Sign {

    // Including zero
    // For simplicity, zero is assumed positive
    // But when a zero money is printed,
    // positive sign is removed
    Positive("+"),
    // Below zero
    Negative("-");

    private final String text;

    private Sign(String sign) {
        this.text = sign;
    }

    public String getText() {
        return text;
    }

    public Sign oppositeOf() {
        if (this == Sign.Positive) {
            return Sign.Negative;
        } else {
            return Sign.Positive;
        }
    }
}
