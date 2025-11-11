/*
 * Author: Saketh Katta
 * Course: CSC 210 - Summer 2025
 * File: Garden.java
 * Purpose: This class manages a garden grid and processes user commands
 * like planting, growing, harvesting, picking, cutting, and printing plants.
 */

package com.gradescope.garden;

public class Garden {
	private int numRows;
	private int numCols;
	private Plant[][] garden;

	/*
	 * Garden constructor
	 * Initializes the garden with a 2D array of the given number of rows and columns.
	 */
	public Garden(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		garden = new Plant[numRows][numCols];
	}

	/*
	 * processCommand(command)
	 * Determines which command was entered and calls the appropriate method to handle it.
	 */
	public void processCommand(String command) {
		command = command.trim();

		if (command.length() == 0) {
			return; // skip empty lines
		}

		String lower = command.toLowerCase();

		// Check which type of command it is and delegate accordingly
		if (lower.startsWith("print")) {
			handlePrint();
		} else if (lower.startsWith("plant")) {
			handlePlant(command);
		} else if (lower.startsWith("grow")) {
			handleGrow(command);
		} else if (lower.startsWith("pick")) {
			handlePick(command);
		} else if (lower.startsWith("cut")) {
			handleCut(command);
		} else if (lower.startsWith("harvest")) {
			handleHarvest(command);
		}
	}

	/*
	 * handlePrint()
	 * Displays the garden by printing the character screen made of plant plots.
	 */
	private void handlePrint() {
		Screen screen = new Screen(numRows * 5, numCols * 5);

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (garden[row][col] != null) {
					char[][] shape = garden[row][col].getRepresentation();
					screen.place(row, col, shape);
				}
			}
		}

		System.out.println("> PRINT");
		screen.print();
	}

	/*
	 * handlePlant(command)
	 * Parses a PLANT command and creates the correct type of Plant object at the given position.
	 */
	private void handlePlant(String command) {
		int open = command.indexOf('(');
		int comma = command.indexOf(',');
		int close = command.indexOf(')');

		// Invalid command format
		if (open == -1 || comma == -1 || close == -1 || comma < open || close < comma) {
			return;
		}

		// Extract coordinates and plant type
		String rowStr = command.substring(open + 1, comma).trim();
		String colStr = command.substring(comma + 1, close).trim();
		String plantType = command.substring(close + 1).trim().toLowerCase();

		int row = Integer.parseInt(rowStr);
		int col = Integer.parseInt(colStr);

		Plant newPlant = null;

		// Check which subclass of plant to create
		if (plantType.equals("sunflower") || plantType.equals("rose") || plantType.equals("daisy")
				|| plantType.equals("tulip") || plantType.equals("iris") || plantType.equals("lily")) {
			newPlant = new Flower(plantType, row, col);
		} else if (plantType.equals("oak") || plantType.equals("willow") || plantType.equals("pine")
				|| plantType.equals("banana") || plantType.equals("coconut")) {
			newPlant = new Tree(plantType, row, col);
		} else if (plantType.equals("tomato") || plantType.equals("yam") || plantType.equals("zucchini")
				|| plantType.equals("lettuce") || plantType.equals("garlic")) {
			newPlant = new Vegetable(plantType, row, col);
		}

		// Only add the plant if it's valid and coordinates are in bounds
		if (newPlant != null && row >= 0 && row < numRows && col >= 0 && col < numCols) {
			garden[row][col] = newPlant;
		}
	}

	/*
	 * handleGrow(command)
	 * Handles all types of GROW commands (grow all, grow specific plant/class, or grow at specific coordinate).
	 */
	private void handleGrow(String command) {
		System.out.println();
		System.out.println("> " + command.substring(0, 4).toUpperCase() + command.substring(4));

		String[] tokens = command.split(" ");
		if (tokens.length < 2) {
			return;
		}

		int times = Integer.parseInt(tokens[1]);

		// Case: grow at a specific coordinate
		if (tokens.length == 3 && tokens[2].startsWith("(")) {
			int open = command.indexOf('(');
			int comma = command.indexOf(',');
			int close = command.indexOf(')');

			if (open == -1 || comma == -1 || close == -1 || comma < open || close < comma) {
				return;
			}

			String rowStr = command.substring(open + 1, comma).trim();
			String colStr = command.substring(comma + 1, close).trim();

			int row = Integer.parseInt(rowStr);
			int col = Integer.parseInt(colStr);

			if (row >= 0 && row < numRows && col >= 0 && col < numCols && garden[row][col] != null) {
				garden[row][col].grow(times);
			} else {
				System.out.println();
				System.out.println("Can't grow there.");
			}
			return;
		}

		// Case: grow all plants
		if (tokens.length == 2) {
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					if (garden[i][j] != null) {
						garden[i][j].grow(times);
					}
				}
			}
		}
		// Case: grow by plant name or class (flower/tree/vegetable)
		else if (tokens.length == 3) {
			String target = tokens[2].toLowerCase();

			if (target.equals("flower") || target.equals("tree") || target.equals("vegetable")) {
				for (int i = 0; i < numRows; i++) {
					for (int j = 0; j < numCols; j++) {
						Plant p = garden[i][j];
						if (p != null && p.getClass().getSimpleName().equalsIgnoreCase(target)) {
							p.grow(times);
						}
					}
				}
			} else {
				for (int i = 0; i < numRows; i++) {
					for (int j = 0; j < numCols; j++) {
						Plant p = garden[i][j];
						if (p != null && p.getName().equalsIgnoreCase(target)) {
							p.grow(times);
						}
					}
				}
			}
		}
	}

	/*
	 * handlePick(command)
	 * Removes a flower from the garden either by coordinates or name.
	 */
	private void handlePick(String command) {
		System.out.println();
		System.out.println("> " + command.substring(0, 4).toUpperCase() + command.substring(4));

		// Case: PICK (r,c)
		if (command.contains("(")) {
			int open = command.indexOf('(');
			int comma = command.indexOf(',');
			int close = command.indexOf(')');

			if (open == -1 || comma == -1 || close == -1 || comma < open || close < comma) {
				return;
			}

			int row = Integer.parseInt(command.substring(open + 1, comma).trim());
			int col = Integer.parseInt(command.substring(comma + 1, close).trim());

			if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
				if (garden[row][col] == null) {
					System.out.println();
					System.out.println("Can't pick there.");
					return;
				} else if (garden[row][col] instanceof Flower) {
					garden[row][col] = null;
				} else {
					System.out.println();
					System.out.println("Can't pick there.");
				}
			}
		}
		// Case: PICK <name> or PICK
		else {
			String[] parts = command.trim().split(" ");
			
			// Pick all flowers
			if (parts.length == 1) {
				for (int r = 0; r < numRows; r++) {
					for (int c = 0; c < numCols; c++) {
						if (garden[r][c] instanceof Flower) {
							garden[r][c] = null;
						}
					}
				}
				return;
			}

			// Pick specific flower by name
			if (parts.length != 2) {
				return;
			}

			String flowerName = parts[1].toLowerCase();

			for (int r = 0; r < numRows; r++) {
				for (int c = 0; c < numCols; c++) {
					Plant plant = garden[r][c];
					if (plant instanceof Flower && plant.name.equalsIgnoreCase(flowerName)) {
						garden[r][c] = null;
					}
				}
			}
		}
	}

	/*
	 * handleCut(command)
	 * Cuts (removes) a tree at the given coordinate.
	 */
	private void handleCut(String command) {
		System.out.println();
		System.out.println("> " + command.substring(0, 3).toUpperCase() + command.substring(3));

		int open = command.indexOf('(');
		int comma = command.indexOf(',');
		int close = command.indexOf(')');

		if (open == -1 || comma == -1 || close == -1 || comma < open || close < comma) {
			return;
		}

		int row = Integer.parseInt(command.substring(open + 1, comma).trim());
		int col = Integer.parseInt(command.substring(comma + 1, close).trim());

		if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
			if (garden[row][col] == null) {
				System.out.println();
				System.out.println("Can't cut there.");
			} else if (garden[row][col] instanceof Tree) {
				garden[row][col] = null;
			} else {
				System.out.println();
				System.out.println("Can't cut there.");
			}
		}
	}

	/*
	 * handleHarvest(command)
	 * Removes vegetables from the garden, either by coordinate or all at once.
	 */
	private void handleHarvest(String command) {
		System.out.println();
		System.out.println("> " + command.substring(0, 7).toUpperCase() + command.substring(7));

		if (command.contains("(")) {
			int open = command.indexOf('(');
			int comma = command.indexOf(',');
			int close = command.indexOf(')');

			if (open == -1 || comma == -1 || close == -1 || comma < open || close < comma) {
				return;
			}

			int row = Integer.parseInt(command.substring(open + 1, comma).trim());
			int col = Integer.parseInt(command.substring(comma + 1, close).trim());

			if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
				if (garden[row][col] == null) {
					System.out.println();
					System.out.println("Can't harvest there.");
				} else if (garden[row][col] instanceof Vegetable) {
					garden[row][col] = null;
				} else {
					System.out.println();
					System.out.println("Can't harvest there.");
				}
			}
		} else {
			// Harvest all vegetables
			for (int r = 0; r < numRows; r++) {
				for (int c = 0; c < numCols; c++) {
					if (garden[r][c] instanceof Vegetable) {
						garden[r][c] = null;
					}
				}
			}
		}
	}

	/*
	 * getGardenGrid()
	 * Returns the internal 2D array of plant objects.
	 */
	public Plant[][] getGardenGrid() {
		return garden;
	}
}
