/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecteuler;

/**
 * From: http://projecteuler.net/problem=15
 * @author rogan2929
 */
public class Problem15 {
    private static long pathCount = 0;
    
    /***
     * Get the result for this problem.
     * @param n Length of one side of the lattice, in segments.
     * @return Number of paths from upper left to lower right corner.
     */
    public static long result(int n) {
        // The number of ordered pairs for a lattice of n segments is always y = (n + 1)^2.
        
        pathCount = 0;
        
        // Get things going by looking both down and right.
        getLatticePathCount(0, 0, n + 1);
        
        return pathCount;
    }
    
    private static void getLatticePathCount(int x, int y, int size) {
        // Try right
        // Try down
        // If none of the above, then we are at the end.
        
        if (x + 1 < size) {
            getLatticePathCount(x + 1, y, size);
        }
        
        if (y + 1 < size) {
            getLatticePathCount(x, y + 1, size);
        }
        
        // Check to see if the end has been reached.
        if (x + 1 == size && y + 1 == size) {
            pathCount++;
        }
    }
}
