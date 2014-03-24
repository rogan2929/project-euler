/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

/**
 *
 * @author scalesr
 */
public class Pair {
    private int x;
    private int y;
    private int value;
    
    public Pair (int x, int y) {
        this.x = x;
        this.y = y;
        this.value = 0;
    }
    
    public Pair (int row, int col, int value) {
        this.x = row;
        this.y = col;
        this.value = value;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.x;
        hash = 19 * hash + this.y;
        hash = 19 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pair{" + "x=" + x + ", y=" + y + ", value=" + value + '}';
    }
}
