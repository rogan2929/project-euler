/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

/**
 * From: http://projecteuler.net/problem=5
 * @author scalesr
 */
public class Problem5 {
    public static int result(int top) {
        int n = 1;
        
        // What number is divisible by all numbers from 1 to 'top'?
        
        while (true) {
            boolean divisible = true;
            
            // Iteratively check each number to see if it goes evenly into 'n'.
            for (int i = 1; i <= top; i++) {
                divisible &= (n % i == 0);
                
                if (!divisible) {
                    break;
                }
            }
            
            if (divisible) {
                break;
            }
            
            n++;
        }
        
        return n;
    }
}
