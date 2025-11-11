# Garden Simulation System

**Course:** CSC 210 - Summer 2025  
**Programming Language:** Java  
**Project Type:** Object-Oriented Simulation with 2D Arrays

## Overview

A Java-based garden simulation system that manages plant growth across a configurable 2D grid of plots. The system demonstrates advanced object-oriented programming principles including inheritance hierarchies, polymorphism, and complex data structure manipulation. Each plot contains a 5×5 character grid that visualizes different plant types with unique growth behaviors.

## Features

### Object-Oriented Design
- **Class hierarchy** with inheritance: `Plant` base class with `Flower`, `Tree`, and `Vegetable` subclasses
- **Polymorphism**: Each plant category implements unique growth patterns
- **Encapsulation**: Separate classes for Garden management, plant behavior, and screen rendering

### Plant Types
The simulation supports **17 different plant species** across 3 categories:

- **Flowers** (6 types): Iris, Lily, Rose, Daisy, Tulip, Sunflower
  - Growth behavior: Bloom outward from center
- **Trees** (5 types): Oak, Willow, Banana, Coconut, Pine
  - Growth behavior: Grow upward from bottom
- **Vegetables** (6 types): Garlic, Zucchini, Tomato, Yam, Lettuce
  - Growth behavior: Grow downward from top

### Command System

The simulation supports **14 command variations** for garden manipulation:

- **PLANT (row,col) [type]** - Plant specific species at coordinates
- **PRINT** - Display entire garden state
- **GROW [num]** - Grow all plants by specified amount
- **GROW [num] (row,col)** - Grow plant at specific location
- **GROW [num] [type]** - Grow specific species
- **GROW [num] [category]** - Grow entire category (flower/tree/vegetable)
- **HARVEST** - Remove all vegetables
- **HARVEST (row,col)** - Remove vegetable at location
- **HARVEST [type]** - Remove specific vegetable species
- **PICK** - Remove all flowers
- **PICK (row,col)** - Remove flower at location
- **PICK [type]** - Remove specific flower species
- **CUT** - Remove all trees
- **CUT (row,col)** - Remove tree at location
- **CUT [type]** - Remove specific tree species

All commands are **case-insensitive**.

## Technical Implementation

### Data Structures
- **2D array of Plant objects**: Represents the garden grid
- **Nested 5×5 character arrays**: Each plot contains a grid for ASCII visualization
- **Coordinate transformation algorithm**: Converts plot coordinates to screen cell coordinates

### Design Patterns
- **Command pattern**: File-based command processing
- **Inheritance hierarchy**: Abstract plant behavior with concrete implementations
- **Strategy pattern**: Different growth strategies for each plant category

### Key Algorithms
- **Boundary checking**: Prevents plant growth beyond plot boundaries
- **Coordinate mapping**: Maps plot positions (row, col) to screen array indices
- **Type-based filtering**: Supports operations on plant categories or specific species

### Error Handling
- Column width validation (maximum 16 plot columns = 80 characters)
- Invalid coordinate detection with descriptive error messages
- Boundary overflow prevention during growth operations

## Project Structure
garden-simulation/
├── RunGarden.java # Main entry point, file parsing
├── Garden.java # Garden management, command processing
├── Plant.java # Abstract base class
├── Flower.java # Flower subclass
├── Tree.java # Tree subclass
├── Vegetable.java # Vegetable subclass
├── Screen.java # 2D character array rendering
└── PA-05.pdf # Assignment specification

## How to Run

### Compilation
javac -d bin RunGarden.java Garden.java Plant.java Flower.java Tree.java Vegetable.java Screen.java


### Execution
java -cp bin com.gradescope.garden.RunGarden input.txt


### Sample Input File
rows: 2
cols: 2
PLANT (0,0) rose
PLANT (0,1) banana
PLANT (1,0) garlic
PRINT
GROW 2
PRINT


## Skills Demonstrated

- **Object-Oriented Programming**: Inheritance, polymorphism, encapsulation, abstraction
- **Data Structures**: 2D arrays, nested data structures, dynamic object management
- **File I/O**: Command-line argument parsing, file reading, input validation
- **Algorithm Design**: Coordinate transformation, boundary checking, growth simulation
- **Error Handling**: Input validation, constraint enforcement, graceful error messages
- **Code Organization**: Multi-class decomposition, separation of concerns
- **Documentation**: UML class diagrams, inline comments, clear method headers

## Design Constraints

- Maximum **80 characters** per row (16 plot columns × 5 cells)
- Each plot is **5×5 characters**
- Plants represented by **lowercase first letter** of species name
- Command processing is **case-insensitive**
- Less than **10 .java files**, each under **275 lines**
- Static methods under **30 lines**

## Grading Criteria

- **60% Correctness**: Passed all public and hidden test cases
- **40% Design Quality**: Class hierarchy diagram (20%), code decomposition (20%)

## Learning Objectives

This project demonstrates proficiency in:
- Designing and implementing class hierarchies to solve complex problems
- Using polymorphism for behavior variation across subclasses
- Managing multi-dimensional data structures
- Processing and validating command-line input
- Creating comprehensive UML class diagrams

---

**Author:** Saketh Katta  
**University:** University of Arizona  
**Major:** Computer Science (Junior)
