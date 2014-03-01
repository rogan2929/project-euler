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
    private static int pathCount = 0;
    
    /***
     * Get the result for this problem.
     * @param b Length of one side of the lattice, in 'blocks'.
     * @return Number of paths from upper left to lower right corner.
     */
    public static int result(int b) {
        // Create the lattice; doesn't matter what's in it.
        int[][] lattice = new int[b][b];
        
        getLatticePathCount(b);
        
        return pathCount;
    }
    
    private static void getLatticePathCount(int b) {
        
    }
}
