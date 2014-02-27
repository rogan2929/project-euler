/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scalesr
 */
public class Problem3 {
    public static long result(long x) {
        ArrayList primes = getPrimes(x);

        return (long)primes.get(primes.size() - 1);
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
}
