/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

import java.util.ArrayList;

/**
 * From: http://projecteuler.net/problem=8
 * @author scalesr
 */
public class Problem7 {
    public static int result(int n) {
        int primeCount = 1;
        int prime = 0;
        int i = 3;
        
        // First prime is 2.
        if (n == 1) {
            return 2;
        }
        
        while (primeCount < n) {
            if (isPrime(i)) {
                primeCount++;
                prime = i;
            }
        
            i += 2;
        }
        
        return prime;
    }

    private static boolean isPrime(int x) {
        boolean isPrime = true;
        
        for (int i = 3; i < x; i++) {
            if (x % i == 0) {
                isPrime = false;
                break;
            }
        }
        
        return isPrime;
    }
}
