/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilera.ryan.accountsystem.utility;

/**
 *
 * @author Ryan Gilera
 */
public class StringUtil {

    public static String formatClientName(String name) {
        // Remove extra spaces in the beginning and at the end
        name = name.trim();
        // Normalise string name to lower case
        name = name.toLowerCase();

        // Converts first letter of each word to capital
        // ex. "ryan gilera" to "Ryan Gilera"
        StringBuilder tempNameHolder = new StringBuilder();
        String[] stringHolderArray = name.split(" ");
        for (String eachWordBeforeSpace : stringHolderArray) {
            char[] eachWordArray = eachWordBeforeSpace.trim().toCharArray();
            eachWordArray[0] = Character.toUpperCase(eachWordArray[0]);
            eachWordBeforeSpace = new String(eachWordArray);

            tempNameHolder.append(eachWordBeforeSpace).append(" ");
        }

        return tempNameHolder.toString();
    }

    public static String removeExtraSpacesInName(String name) {
        // First it trims extra spaces in the beginning and end
        // Replaces all 1 or more spaces in between to single space
        // Ex. "ryan    gilera   " becomes "ryan gilera"
        return name.trim().replaceAll("\\s+", " ");
    }
}
