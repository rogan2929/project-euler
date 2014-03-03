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
        
        for (int i = 0; i < 1000; i++) {
            //sum += letterCount(i);
        }
        
        letterCount(1000);
        letterCount(312);
        letterCount(456);
        letterCount(200);
        letterCount(110);
        letterCount(18);
        letterCount(754);
        
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
        
        if (thousand > 0) {
            number = ones[thousand - 1] + " thousand";
        }
        
        if (hundred > 0) {
            if (!number.equals("")) {
                number += " and ";
            }
            
            number += ones[hundred - 1] + " hundred";
        }
        
        if (ten > 0) {
            if (!number.equals("")) {
                number += " and ";
            }
            
            // Special case for the teens...
            if (ten == 1 && one > 0) {
                
            }
            else {
                number += tens[ten - 1];
            }
        }
        
        System.out.println(number);
        
        // Cut out the spaces and return the number of letters.
        return (number.replace(" ", "")).length();
    }
}
