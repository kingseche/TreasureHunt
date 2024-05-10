# Treasure Hunt - Carbon IT
## Overview
The Treasure Hunt Game is a Java application that simulates adventurers searching for treasures on a map. 
The adventurers move on the map according to a predefined sequence of movements, collecting treasures along the way.

## Features
Read input file containing map details, including dimensions, mountains, treasures, and adventurers.
Simulate movement for each adventurer according to their movement sequence.
Handle collisions with mountains and collection of treasures.
Write the final state of the map with adventurers and treasures to an output file.

## Project Structure
### The project consists of the following components:

Main Java Classes: Contains the main logic and functionality of the game.
Test Classes: Contains JUnit tests to verify the correctness of the implementation.
Resources: Contains sample input files for testing.

## Usage
Clone the repository to your local machine.
Import the project into your Java IDE (I used IntelliJ).
Add the input file and run the Game class to execute the Treasure Hunt Game.
Check the output.txt file in the resources directory to view the final state of the map.

## Input File Format
### The input file should follow a specific format:
The first line specifies the dimensions of the map (e.g., C-3-4).
Subsequent lines define mountains (e.g., M-1-0), treasures (e.g., T-0-3-2), and adventurers (e.g., A-Lara-1-1-S-AADADAGGA).

### Output File Format:
The output file contains the final state of the map with adventurers and treasures. 
Each line represents a cell on the map or an adventurer's position.

## Tests
JUnit tests are provided to validate the functionality of the game components. Each test verifies a specific aspect of the game, such as adventurer movement, mountain collisions, treasure collection, and map boundary handling.



