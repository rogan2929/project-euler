/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

/**
 * From: https://projecteuler.net/problem=17
 *
 * @author Parents
 */
public class Problem17 {

    private static String[] ones = {
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
    };

    private static String[] teens = {
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "ninteen"
    };

    private static String[] tens = {
        "ten",
        "twenty",
        "thirty",
        "forty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety"
    };

    public static int result() {
        int sum = 0;

        for (int i = 1; i <= 1000; i++) {
            sum += letterCount(i);
        }

        return sum;
    }

    private static int letterCount(int n) {
        // Use "and" in writing numbers out. E.g., "one hundred and forty five"

        int thousand = n / 1000;
        n = n - thousand * 1000;

        // Extract digits from number.
        int hundred = n / 100;
        n = n - hundred * 100;

        int ten = n / 10;
        n = n - ten * 10;

        int one = n;

        String number = "";

        // Thousands.
        if (thousand > 0) {
            number = ones[thousand - 1] + " thousand";
        }

        // Hundreds
        if (hundred > 0) {
            if (!number.equals("")) {
                number += " and ";
            }

            number += ones[hundred - 1] + " hundred";
        }

        // Ones & tens.
        if (ten > 1 || (ten == 1 && one == 0)) {
            if (!number.equals("")) {
                number += " and ";
            }

            number += tens[ten - 1];
        }

        // Special case for the teens.
        if (ten == 1 && one > 0) {
            if (!number.equals("")) {
                number += " and ";
            }
            
            number += teens[one - 1];
        }
        else if (one > 0) {
            if (!number.equals("") && (thousand > 0 || hundred > 0 && ten == 0)) {
                number += " and ";
            }
            else if (!number.equals("")) {
                number += " ";
            }
            
            number += ones[one - 1];
        }

        System.out.println(number);

        // Cut out the spaces and return the number of letters.
        return (number.replace(" ", "")).length();
    }
}
