/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

/**
 *
 * @author scalesr
 */
public class Problem14 {

    public static int result(int ceiling) {
        int longest = 0;
        int start = 0;

        for (int i = 1; i < ceiling; i++) {
            //System.out.print("c(" + Integer.toString(i) + "): ");

            int length = getCollatzSequenceLength(i);
            
            //System.out.print(length);

            //System.out.println(' ');

            if (length >= longest) {
                longest = length;
                start = i;
            }
        }

        return start;
    }

    private static int getCollatzSequenceLength(long n) {
        int length = 0;

        while (n >= 1) {
            length++;

            //System.out.print(Integer.toString(n) + ", ");

            if (n == 1) {
                break;
            }
            
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }

        return length;
    }
}
