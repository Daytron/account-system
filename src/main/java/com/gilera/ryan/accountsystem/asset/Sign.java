/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.asset;

/**
 *
 * @author Ryan Gilera
 */
public enum Sign {

    // Including zero
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
