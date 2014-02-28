/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

import java.util.ArrayList;

/**
 * From: http://projecteuler.net/problem=12
 * @author rogan2929
 */
public class Problem12 {
    static int result(int divisorCount) {
        int count = 0;
        int n = 1;
        int sum = 0;
        
        while (count < divisorCount) {
            sum = getTriangleSum(n);
            count = getFactorCount(sum);
            
            n++;
        }
        
        return sum;
    }
    
    static int getTriangleSum(int n) {
        // From induction hypothesis...
        System.out.println(n);
        return n * (n + 1) / 2;
    }
    
    static int getFactorCount(int n) {
        // From: http://mathcentral.uregina.ca/QQ/database/QQ.02.06/joe1.html
        
        ArrayList primes = getPrimes(n);
        int factorCount = 0;
        
        
        
        return factorCount;
    }
    
    public static ArrayList getPrimes(long x) {
        ArrayList primes = new ArrayList();
        
        for (long i = 2; i <= x; i++) {
            
            // See if i divides into x.
            if (x % i == 0) {
                primes.add(i);
                
                x /= i;
                i -= 1;
            }
        }

        return primes;
    }
    
//    static ArrayList getFactors(int n) {
//        ArrayList factors = new ArrayList();
//        
//        for (int i = 1; i <= n; i++) {
//            if (n % i == 0) {
//                factors.add(i);
//            }
//        }
//        
//        return factors;
//    }
}
