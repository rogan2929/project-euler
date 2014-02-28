/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

/**
 * From: http://projecteuler.net/problem=6
 * @author scalesr
 */
public class Problem6 {
    public static int result(int n) {
        // Sum of squares
        // n(n + 1)(2n + 1) / 6
        
        // Square of sum
        // (n(n + 1) / 2) ^ 2
        
        int squaredSum = (int)Math.pow(n * (n + 1) / 2, 2);
        int sumSquares = n * (n + 1) * (2 * n + 1) / 6;
        
        return squaredSum - sumSquares;
    }
}
