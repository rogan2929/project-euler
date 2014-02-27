/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author scalesr
 */
public class Problem4 {

    public static int result(int power) {
        ArrayList palindromes = new ArrayList<>();
        int min = (int)Math.pow(10, power);
        int max = (int)Math.pow(10, power + 1);
        
        for (int i = min; i < max; i++) {
            for (int j = min; j < max; j++) {
                String product = Integer.toString(i * j);

                // Split the string up and determine if it's a palindrome.
                String first = product.substring(0, product.length() / 2);
                String second = product.substring(product.length() / 2, product.length());
                String reverse = new StringBuffer(second).reverse().toString();
                
                if (first.equals(reverse) && !palindromes.contains(product)) {
                    palindromes.add(i * j);
                }
            }
        }
        
        Collections.sort(palindromes);

        return (int)palindromes.get(palindromes.size() - 1);
    }
}
