/*
 * Author: Saketh Katta
 * Course: CSC 210 - Summer 2025
 * File: Vegetable.java
 * Purpose: This class represents a Vegetable, which is a type of Plant.
 * Vegetables grow vertically downward from the top of the 5x5 plot.
 */

package com.gradescope.garden;

public class Vegetable extends Plant {

    /*
     * Constructor: Vegetable
     * Initializes the vegetable with its symbol at the top center of the 5x5 plot.
     * Sets initial growth level to 1.
     */
    public Vegetable(String name, int row, int col) {
        super(name, row, col);
        plot[0][2] = symbol;  // First part of the vegetable appears at the top
        growth = 1;
    }

    /*
     * grow(times)
     * Grows the vegetable downward from the top by placing the symbol one row lower each time,
     * up to a maximum depth of 5.
     */
    @Override
    public void grow(int times) {
        for (int t = 0; t < times; t++) {
            if (growth < 5) {
                int newRow = growth;  // Grow downward into next row
                if (newRow < 5) {
                    plot[newRow][2] = symbol;
                    growth++;
                }
            }
        }
    }
}
