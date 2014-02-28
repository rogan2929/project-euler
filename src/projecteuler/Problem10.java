/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

import java.util.ArrayList;

/**
 * From: http://projecteuler.net/problem=10
 * @author rogan2929
 */
public class Problem10 {

    public static long result(int n) {
        ArrayList primes = getPrimesBelowN(n);
        long sum = 0;
        
        for (int i = 0; i < primes.size(); i++) {
            sum += (int)primes.get(i);
        }
        
        return sum;
    }
    
    private static ArrayList getPrimesBelowN(int n) {
        ArrayList primes = new ArrayList();
        
        if (n >= 2) {
            primes.add(2);
        }
        
        for (int i = 3; i < n; i += 2) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        
        return primes;
    }

    private static boolean isPrime(int n) {
        boolean isPrime = true;

        for (int i = 3; i < n / 2; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }

}
