/*
 * Author: Saketh Katta
 * Course: CSC 210 - Summer 2025
 * File: Screen.java
 * Purpose: This class handles rendering a grid of characters that represent
 * the garden. It allows plants to be placed on the screen and prints the
 * final result to the console.
 */

package com.gradescope.garden;

public class Screen {
    private char[][] screen;

    /*
     * Screen constructor
     * Initializes a blank character grid (filled with '.') with given height and width.
     */
    public Screen(int height, int width) {
        screen = new char[height][width];

        // Fill the entire screen with dots
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                screen[i][j] = '.';
            }
        }
    }

    /*
     * place(row, col, rep)
     * Places a 5x5 character plot (representing a plant) into the screen at the specified row and column.
     */
    public void place(int row, int col, char[][] rep) {
        int startRow = row * 5;  // Convert grid coordinates to pixel start index
        int startCol = col * 5;

        // Copy each character from the plant's 5x5 plot into the screen
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                screen[startRow + i][startCol + j] = rep[i][j];
            }
        }
    }

    /*
     * print()
     * Prints the entire screen grid line by line to the console.
     */
    public void print() {
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.println(); // Move to next line after each row
        }
    }
}
