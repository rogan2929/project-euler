/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

/**
 * From: http://projecteuler.net/problem=9
 * @author rogan2929
 */
public class Problem9 {
    public static int result() {
        // So, here's what we know.
        // a < b < c
        // a + b + c = 1000
        // a^2 + b^2 = c^2
        
        int a = 0;
        int b = 0;
        int c = 0;
        boolean found = false;
        
        for (b = 1; b < 999; b++) {
            for (a = 1; a < b; a++) {
                if (evaluate(a, b)) {
                    found = true;
                    break;
                }
            }
            
            if (found) {
                break;
            }
        }
        
        c = 1000 - a - b;
        
        return a * b * c;
    }
    
    private static boolean evaluate(int a, int b) {        
        return 500000 - 1000 * a - 1000 * b + a * b == 0;
    }
}
