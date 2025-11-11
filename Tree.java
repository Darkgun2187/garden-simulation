/*
 * Author: Saketh Katta
 * Course: CSC 210 - Summer 2025
 * File: Tree.java
 * Purpose: This class represents a Tree, which is a type of Plant.
 * Trees grow vertically upward with each growth stage adding to their trunk.
 */

package com.gradescope.garden;

public class Tree extends Plant {

    /*
     * Constructor: Tree
     * Initializes the tree with its symbol at the bottom center of the 5x5 plot.
     * Starts growth level at 1 to reflect the initial trunk.
     */
    public Tree(String name, int row, int col) {
        super(name, row, col);
        plot[4][2] = symbol;  // Base of the trunk starts at bottom center
        growth = 1;
    }

    /*
     * grow(times)
     * Grows the tree upward from the base by placing the symbol one row higher per growth,
     * up to a maximum height of 5.
     */
    @Override
    public void grow(int times) {
        for (int t = 0; t < times; t++) {
            if (growth < 5) {
                int newRow = 4 - growth;  // Calculate the new row to grow into
                if (newRow >= 0) {
                    plot[newRow][2] = symbol;  // Add to the trunk
                    growth++;
                }
            }
        }
    }
}
