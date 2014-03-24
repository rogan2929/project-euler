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

    // Rows that have been examined by the algorithm.
    ArrayList<Integer> evaluatedRows = new ArrayList();

    // Anchor points that have been evaluated.
    ArrayList<Pair> evalulatedAnchorPoints = new ArrayList();

    // Largest path sum found
    long largestPathSum = 0L;

    public static long result() {
        // Read the pyramid (triangle) in.        
        Problem18 problem = new Problem18();

        problem.readInput();

        problem.findLargestPathSum();

        return problem.getLargestPathSum();
    }

    public long getLargestPathSum() {
        return largestPathSum;
    }

    private void findLargestPathSum() {
        Pair anchorPoint = new Pair(0, 0, Integer.MAX_VALUE);

        // Start looking at each possible anchor point and find the largest path.
        while (anchorPoint != null) {
            anchorPoint = getNextAnchorPoint(anchorPoint);

            // Clear an evaluated rows.
            this.evaluatedRows.clear();

            long pathSum = determinePathValue(anchorPoint);

            // Determine highest value path that goes through this anchor point.
            if (pathSum > this.largestPathSum) {
                this.largestPathSum = pathSum;
            }
        }
    }

    /**
     * *
     * Construct a path that uses the current anchor point and determine its
     * sum.
     *
     * @return
     */
    private long determinePathValue(Pair anchorPoint) {
        ArrayList<Pair> path = new ArrayList();
        long pathSum = 0L;
        int pathLength = this.triangle.size();

        // Add start point. (AKA... the top of the triangle.)
        path.add(new Pair(0, 0, triangle.get(0)[0]));

        // Next add the anchor point.
        path.add(anchorPoint);

        // Add both rows to the evaluatedRows array.
        this.evaluatedRows.add(0);
        this.evaluatedRows.add(anchorPoint.getX());

        while (path.size() < pathLength) {
            // Find the next largest value in the path, using the last known value as a reference.
            path.add(getLargestP(anchorPoint, path.get(path.size() - 1)));
        }

        // Add 'em up.
        for (int i = 0; i < pathLength; i++) {
            pathSum += path.get(i).getValue();
        }

        return pathSum;
    }

    /**
     * *
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
                }

                triangle.add(rowIntegers);

                //System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Problem18.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Find the largest p that is not on a previous evaluated row.
     *
     * @param reference Reference point used to determine if the next largest p
     * is on the same path as the anchor point.
     * @return largest p
     */
    private Pair getLargestP(Pair anchorPoint, Pair reference) {
        int largestValue = 0;
        Pair largest = null;
        int row;
        int col;

        for (row = 0; row < triangle.size(); row++) {
            if (!this.evaluatedRows.contains(row)) {
                for (col = 0; col < this.triangle.get(row).length; col++) {
                    int value = this.triangle.get(row)[col];

                    Pair p = new Pair(row, col, value);

                    if (value > largestValue && !p.equals(anchorPoint) && !p.equals(reference)) {
                        // Test if p falls on the same path. This is determined by examining the reference pair's triangle of divergence.
                        boolean onPath;
                        int refRow = reference.getX();
                        int refCol = reference.getY();
                        
                        int maxRow = this.triangle.size();
                        
                        if (row > refRow) {
                            int i;
                            int j;
                            
                            if (refRow > refCol) {
                                i = refRow;
                                j = refCol;
                            }
                            else {
                                i = refCol;
                                j = refRow;
                            }
                            
                            // Get bottom right bound of triangle.
                            for (; i < maxRow ; i++) {
                                j++;
                            }
                            
                            Pair lowerLeft = new Pair(i, j);
                            
                            i = 0;
                        }
                        else {
                            
                        }

//                        // Distance to bottom
//                        int k = this.triangle.size() - 1 - refRow;
//                        // Distance from right
//                        int l = this.triangle.get(refRow).length - 1 - refCol;
//
//                        // Calculate the bounds of the divergence triangle from the given pair.
//                        // Lower right triangle.
//                        if (row > refRow) {
//                            onPath = (row - refRow <= k && col - refCol <= l);
//                        } else {
//                            // Upper left triangle
//                            //int k = this.triangle.size() - 1 - row;
//                            
//                            k = this.triangle.size() - 1 - row;
//                            l = this.triangle.get(row).length - 1 - col;
//                            
//                            onPath = (refRow - row <= k && refCol - col <= k);
//                        }

//                        if (onPath) {
//                            largestValue = value;
//                            largest = p;
//                        }
                    }
                }
            }
        }

        // Mark that this row has been evaluated.
        if (largest != null) {
            this.evaluatedRows.add(row);
        }

        return largest;
    }

    /**
     * *
     * Retrieves the next largest anchor point in the triangle.
     *
     * @param anchorPoint Current anchor point.
     */
    private Pair getNextAnchorPoint(Pair anchorPoint) {
        int largestValue = 0;
        Pair nextAnchorPoint = null;
        int row;
        int col;

        for (row = 1; row < triangle.size(); row++) {
            for (col = 0; col < this.triangle.get(row).length; col++) {
                int value = this.triangle.get(row)[col];
                Pair p = new Pair(row, col, value);

                // Look for the next largest anchor point that has not already been evaluated.
                if (value > largestValue && value <= anchorPoint.getValue() && !this.evalulatedAnchorPoints.contains(p)) {
                    largestValue = value;

                    nextAnchorPoint = p;
                }
            }
        }

        if (nextAnchorPoint != null) {
            this.evalulatedAnchorPoints.add(nextAnchorPoint);
        }

        //this.currentAnchorPoint = nextAnchorPoint;
        return nextAnchorPoint;
    }

    public ArrayList<Integer[]> getTriangle() {
        return triangle;
    }
}
