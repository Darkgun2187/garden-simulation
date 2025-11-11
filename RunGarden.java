/*
 * Author: Saketh Katta
 * Course: CSC 210 - Summer 2025
 * File: RunGarden.java
 * Purpose: This program reads garden simulation commands from an input file,
 * initializes the garden based on the specified dimensions, and processes each
 * command such as PLANT, GROW, PICK, etc. through the Garden class.
 */

package com.gradescope.garden;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RunGarden {

	/*
	 * main(args)
	 * Reads the garden file name from command-line arguments, parses the input file,
	 * initializes the garden, and processes each command through the Garden class.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			return; // Exit if no input file is provided
		}

		try {
			File inputFile = new File(args[0]);
			Scanner scanner = new Scanner(inputFile);

			int numRows = 0;
			int numCols = 0;

			// Read input lines to extract garden dimensions
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();

				if (line.toLowerCase().startsWith("rows:")) {
					String[] parts = line.split(":");
					numRows = Integer.parseInt(parts[1].trim());
				} else if (line.toLowerCase().startsWith("cols:")) {
					String[] parts = line.split(":");
					numCols = Integer.parseInt(parts[1].trim());
				}

				// Stop reading once both dimensions are set
				if (numRows > 0 && numCols > 0) {
					break;
				}
			}

			// Reject too-wide gardens (as per spec)
			if (numCols > 16) {
				System.out.println("Too many plot columns.");
				scanner.close();
				return;
			}

			// Initialize the garden with given size
			Garden garden = new Garden(numRows, numCols);

			boolean firstCommand = true;

			// Read and process remaining commands
			while (scanner.hasNextLine()) {
			    String command = scanner.nextLine().trim();

			    if (!command.isEmpty()) {
			        // Add blank line before PRINT (after the first)
			        if (!firstCommand && command.toLowerCase().startsWith("print")) {
			            System.out.println();
			        }

			        garden.processCommand(command); // Delegate to Garden
			        firstCommand = false;
			    }
			}

			scanner.close();

		} catch (FileNotFoundException e) {
		    System.out.println("File not found: " + args[0]);
		}
	}
}
