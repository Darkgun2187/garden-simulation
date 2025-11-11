/*
 * Author: Saketh Katta
 * Course: CSC 210 - Summer 2025
 * File: Flower.java
 * Purpose: This class represents a Flower, a type of Plant in the garden simulation.
 * It defines how the flower grows and displays itself on the 5x5 plot.
 */

package com.gradescope.garden;

public class Flower extends Plant {

	/*
	 * Constructor: Flower
	 * Initializes a Flower object by calling the Plant constructor and placing
	 * the initial symbol at the center of the 5x5 plot.
	 */
	public Flower(String name, int row, int col) {
		super(name, row, col);
		plot[2][2] = name.charAt(0); // place symbol at center of 5x5 plot
	}

	/*
	 * grow(num) -- Grows the flower by a given number of times.
	 * For "rose", grows in a plus shape (cross). For all others, grows outward
	 * using Manhattan distance from the center.
	 */
	@Override
	public void grow(int num) {
	    for (int g = 0; g < num; g++) {
	        growth++; // increase growth level by 1

	        if (name.equals("rose")) {
	            // Grow vertical stem of rose
	            for (int i = 2 - growth; i <= 2 + growth; i++) {
	                if (i >= 0 && i < 5) {
	                    plot[i][2] = symbol;
	                }
	            }

	            // Grow horizontal arms of rose
	            for (int j = 2 - growth; j <= 2 + growth; j++) {
	                if (j >= 0 && j < 5) {
	                    plot[2][j] = symbol;
	                }
	            }
	        } else {
	            // Grow outward in diamond pattern based on Manhattan distance
	            for (int i = 0; i < 5; i++) {
	                for (int j = 0; j < 5; j++) {
	                    if (Math.abs(i - 2) + Math.abs(j - 2) <= growth) {
	                        plot[i][j] = symbol;
	                    }
	                }
	            }
	        }
	    }
	}
}
