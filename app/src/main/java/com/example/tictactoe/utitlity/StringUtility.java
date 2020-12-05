package com.example.tictactoe.utitlity;

public class StringUtility {

    //Converting integer values to a combined String
    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }

    //Checking whether a given string is empty or null
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
