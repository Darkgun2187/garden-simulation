/*
 * Author: Saketh Katta
 * Course: CSC 210 - Summer 2025
 * File: Plant.java
 * Purpose: This abstract class defines the common structure and behavior for all plant types
 * in the garden simulation. Subclasses like Flower, Tree, and Vegetable extend this class and
 * implement the grow behavior specific to their type.
 */

package com.gradescope.garden;

public abstract class Plant {

    // Common plant properties
    protected String name;
    protected int row;
    protected int col;
    protected char[][] plot;
    protected int growth = 0;
    protected char symbol;

    /*
     * Plant constructor
     * Initializes a 5x5 plot and sets the name, position, and display symbol of the plant.
     */
    public Plant(String name, int row, int col) {
        this.name = name;
        this.symbol = name.toLowerCase().charAt(0); // first letter as display symbol
        this.row = row;
        this.col = col;
        this.plot = new char[5][5];

        // Fill the 5x5 plot with default '.' characters
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                plot[i][j] = '.';
            }
        }
    }

    /*
     * getName()
     * Returns the name of the plant.
     */
    public String getName() {
        return name;
    }

    /*
     * getRow()
     * Returns the row position of the plant.
     */
    public int getRow() {
        return row;
    }

    /*
     * getCol()
     * Returns the column position of the plant.
     */
    public int getCol() {
        return col;
    }

    /*
     * getRepresentation()
     * Returns the 5x5 character plot that visually represents the plant.
     */
    public char[][] getRepresentation() {
        return plot;
    }

    /*
     * isFlower(), isTree(), isVegetable()
     * These default to false and can be overridden in subclasses if needed.
     */
    public boolean isFlower() {
        return false;
    }

    public boolean isTree() {
        return false;
    }

    public boolean isVegetable() {
        return false;
    }

    /*
     * grow(times)
     * Abstract method that must be implemented by subclasses to define how the plant grows.
     */
    public abstract void grow(int times);
}
