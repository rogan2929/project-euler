/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

/**
 * From: http://projecteuler.net/problem=8
 * @author scalesr
 */
public class Problem8 {
    public static int result(String number) {
        int a, b, c, d, e;
        int largestProduct = 0;
        
        for (int i = 0; i < number.length() - 5; i++) {
            a = Character.getNumericValue(number.charAt(i));
            b = Character.getNumericValue(number.charAt(i + 1));
            c = Character.getNumericValue(number.charAt(i + 2));
            d = Character.getNumericValue(number.charAt(i + 3));
            e = Character.getNumericValue(number.charAt(i + 4));
            
            int product = a * b * c * d * e;
            
            if (product > largestProduct) {
                largestProduct = product;
            }
        }
        
        return largestProduct;
    }
}
