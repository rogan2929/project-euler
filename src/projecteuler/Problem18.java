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
    ArrayList<Integer> evaluatedRows = new ArrayList();

    // Current largest p in the triangle.
    Pair largestP = new Pair(0, 0, Integer.MAX_VALUE);

    int pCount;

    public static long result() {
        // Read the pyramid (triangle) in.        
        Problem18 problem = new Problem18();

        problem.readInput();

        problem.evaluateTrianglePaths();

        return 0L;
    }

    private void evaluateTrianglePaths() {

    }

    /**
     * *
     * Read input data into the triangle array list.
     */
    private void readInput() {
        try {
            BufferedReader br = new BufferedReader(new FileReader((this.getClass().getResource("Problem18.txt")).getFile()));

            String line;

            this.pCount = 0;

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                Integer[] rowIntegers = new Integer[numbers.length];

                for (int col = 0; col < numbers.length; col++) {
                    rowIntegers[col] = Integer.parseInt(numbers[col]);
                    this.pCount++;
                }

                triangle.add(rowIntegers);
            }

            System.out.println(this.pCount);
        } catch (IOException ex) {
            Logger.getLogger(Problem18.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *
     * Find the largest p that is not on a previous evaluated row, and is less
     * than or equal to the previous largest p.
     *
     * @return largest value of p
     */
    private void getNextLargestP() {
        int largestValue = 0;
        Pair nextLargestP = this.largestP;
        int row;
        int col;

        // Start at one, since we already have the first entry in our path, which is triangles[0][0].
        for (row = 1; row < triangle.size(); row++) {
            if (!this.evaluatedRows.contains(row)) {
                for (col = 0; col < this.triangle.get(row).length; col++) {
                    int value = this.triangle.get(row)[col];

                    if (value > largestValue && value <= this.largestP.getValue()) {
                        largestValue = value;
                        nextLargestP = new Pair(row, col, value);
                    }
                }

                this.evaluatedRows.add(row);
            }
        }

        this.largestP = nextLargestP;
    }

    public ArrayList<Integer[]> getTriangle() {
        return triangle;
    }
}
