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
            path.add(getLargestP(anchorPoint));
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
     * @return largest p
     */
    private Pair getLargestP(Pair anchorPoint) {
        int largestValue = 0;
        Pair largest = null;
        int row;
        int col;

        for (row = 0; row < triangle.size(); row++) {
            if (!this.evaluatedRows.contains(row)) {
                for (col = 0; col < this.triangle.get(row).length; col++) {
                    int value = this.triangle.get(row)[col];

                    Pair p = new Pair(row, col, value);

                    if (value > largestValue) {
                        // Test if p falls on the same path. This is determined by examining the anchor point's triangles of divergence.
                        boolean onPath;
                        
                        // Triangles of divergence from the anchor point.
                        Pair[] leftAnchorTriangle = new Pair[3];
                        Pair[] rightAnchorTriangle = new Pair[3];

                        // Last row of the triangle.
                        int h = this.triangle.size() - 1;

                        // Determine the bounds of the triangle defined by a.
                        int k = h - anchorPoint.getX();         // Distance to bottom.
                        int l = anchorPoint.getY();             // Distance to left side.
                        
                        leftAnchorTriangle[0] = new Pair(anchorPoint.getX() - l, 0);          
                        leftAnchorTriangle[1] = new Pair(anchorPoint.getX(), 0);           
                        leftAnchorTriangle[2] = anchorPoint;
                        
                        rightAnchorTriangle[0] = anchorPoint;
                        rightAnchorTriangle[1] = new Pair(h, anchorPoint.getY());
                        rightAnchorTriangle[2] = new Pair(h, anchorPoint.getY() + k);
                        
                        // See if p falls within either triangle of divergence.
                        onPath = isPointInTriangle(leftAnchorTriangle[0], leftAnchorTriangle[1], leftAnchorTriangle[2], p);
                        onPath |= isPointInTriangle(rightAnchorTriangle[0], rightAnchorTriangle[1], rightAnchorTriangle[2], p);

                        if (onPath) {
                            largestValue = value;
                            largest = p;
                        }
                    }
                }
            }
        }

        // Mark that this row has been evaluated.
        if (largest != null) {
            this.evaluatedRows.add(largest.getX());
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

    // From: http://stackoverflow.com/questions/2049582/how-to-determine-a-point-in-a-triangle
    private float sign(Pair a, Pair b, Pair c) {
        return (a.getX() - c.getX()) * (b.getY() - c.getY()) - (b.getX() - c.getX()) * (a.getY() - c.getY());
    }

    private boolean isPointInTriangle(Pair a, Pair b, Pair c, Pair ref) {
        boolean b1, b2, b3;

        b1 = sign(ref, a, b) < 0.0F;
        b2 = sign(ref, b, c) < 0.0F;
        b3 = sign(ref, c, a) < 0.0F;

        return ((b1 == b2) && (b2 == b3));
    }
}
