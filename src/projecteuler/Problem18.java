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
                        
                        // Determine the divergence triangle for either the reference point or p.
                        // Which depends on whichever is higher in the triangle.
                        
                        // Triangle vertices...
                        Pair a;     // Top point of the triangle.
                        Pair b;     // Lower left corner
                        Pair c;     // Lower right corner.
                        
                        
                        Pair ref;   // Point (pair) to be evalulated.
                        
                        if (p.getX() < reference.getX()) {
                            a = p;
                            ref = reference;
                        }
                        else {
                            a = reference;
                            ref = p;
                        }
                        
                        // Last row of the triangle.
                        int h = this.triangle.size() - 1;
                        
                        // Determine the bounds of the triangle defined by a.
                        int k = h - a.getX();        // Distance to bottom.
                        
                        // b = a(h, c)
                        b = new Pair(this.triangle.size() - 1, a.getY());
                        
                        // c = b(r, c + k)
                        c = new Pair(b.getX(), b.getY() + k);

                        // Now, see if 'ref' falls within the triangle defined with vertices 'abc'.
                        onPath = (ref.getX() <= b.getX() && ref.getY() <= c.getY());
                        
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
