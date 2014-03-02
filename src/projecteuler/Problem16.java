/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * From: http://projecteuler.net/problem=16
 * @author Parents
 */
public class Problem16 {
    public static int result(int exponent) {
        BigDecimal d = new BigDecimal(Math.pow(2, exponent));
        String dstr = d.toPlainString();
        int sum = 0;
        
        for (int i = 0; i < dstr.length(); i++) {
            sum += Integer.parseInt(dstr.substring(i, i + 1));
        }
        
        return sum;
    }
}
