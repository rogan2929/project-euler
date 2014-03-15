/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecteuler;

import helpers.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * From: http://projecteuler.net/problem=18
 *
 * @author scalesr
 */
public class Problem18 {

    // Contains the triangle, or pyramid structure and values.
    ArrayList<Integer[]> triangle = new ArrayList();
    
    // Contains each constructed path, where a path is an array of Pairs.
    ArrayList<Pair[]> foundPaths = new ArrayList();
    
    // Rows that have been examined by the algorithm.
    int[] evaluatedRows;
    
    // Current largest p in the triangle.
    Pair largestP;

    public static long result() {
        // Read the pyramid (triangle) in.        
        Problem18 problem = new Problem18();

        problem.readInput();

        return 0L;
    }

    /***
     * Read input data into the triangle array list.
     */
    private void readInput() {
        try {
            BufferedReader br = new BufferedReader(new FileReader((this.getClass().getResource("Problem18.txt")).getFile()));

            String line;

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                Integer[] rowIntegers = new Integer[numbers.length];

                for (int col = 0; col < numbers.length; col++) {
                    rowIntegers[col] = Integer.parseInt(numbers[col]);
                    System.out.print(numbers[col] + " ");
                }
                
                triangle.add(rowIntegers);
                
                System.out.println();
            }
            
            // Set up the evaluated rows structure.
            evaluatedRows = new int[triangle.size()];
        } catch (IOException ex) {
            Logger.getLogger(Problem18.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /***
     * Find the largest p that is not on a previous evaluated row.
     * @return largest value of p
     */
    private void getLargestP() {
        int largestValue = 0;
        int row;
        int col;
        
        // Start at one, since we already have the first entry in our path, which is triangles[0][0].
        for (row = 1; row < triangle.size(); row++) {
            if (!Arrays.asList(this.evaluatedRows).contains(row)) {
            }
        }
    }
    
    public ArrayList<Integer[]> getTriangle() {
        return triangle;
    }
}
