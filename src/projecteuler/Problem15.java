/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

import helpers.Pair;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * From: http://projecteuler.net/problem=15
 *
 * @author rogan2929
 */
public class Problem15 {
    final private static Map<Pair, BigInteger> calculatedPairs = new HashMap<>();

    /**
     * *
     * Get the result for this problem.
     *
     * @param n Length of one side of the lattice, in segments.
     * @return Number of paths from upper left to lower right corner.
     */
    public static BigInteger result(int n) {
        return getLatticePathCount(0, 0, n);
    }

    private static long getLatticePathCount(int n) {
        // Solve using Pascal's Triangle.

        // Create the lattice with one extra row and column.
        long[][] grid = new long[n + 1][n + 1];

        // Fill edges with ones.
        for (int i = 0; i < n + 1; i++) {
            grid[0][i] = 1;
            grid[i][0] = 1;
        }

        // Fill in the array.
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }

        return grid[20][20];
    }

    /**
     * *
     * Brute force recursive method for determining how many paths there are
     * across a lattice of arbitrary size.
     *
     * @param x
     * @param y
     * @param size
     */
    private static BigInteger getLatticePathCount(int x, int y, int size) {
        // Try right
        // Try down
        // If none of the above, then we are at the end.

        BigInteger pathCount = BigInteger.ZERO;
        Pair pair = new Pair(x, y);
        
        // Make use of memoization to *drastically* cut down on execution time.
        if (calculatedPairs.containsKey(pair)) {
            return calculatedPairs.get(pair);
        }

        // Check to see if the end has been reached.
        if (x == size && y == size) {
            return BigInteger.ONE;
        }

        if (x + 1 <= size) {
            //pathCount += getLatticePathCount(x + 1, y, size);
            pathCount = pathCount.add(getLatticePathCount(x + 1, y, size));
        }
        
        if (y + 1 <= size) {
            //pathCount += getLatticePathCount(x, y + 1, size);
            pathCount = pathCount.add(getLatticePathCount(x, y + 1, size));
        }
        
        calculatedPairs.put(pair, pathCount);

        return pathCount;
    }
}
