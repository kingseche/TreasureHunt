package main.java.com.carbonit.game.service;

import main.java.com.carbonit.game.model.Adventurer;
import main.java.com.carbonit.game.model.Cell;
import main.java.com.carbonit.game.model.Map;
import main.java.com.carbonit.game.model.TypeCell;

import java.util.List;

public class MapService {
    private Map map;

    public MapService(List<String> lines) {
        try {
            initializeMap(lines);
        } catch (Exception e) {
            System.err.println("An error occurred while initializing the map: " + e.getMessage());
        }
    }

    // Initialize the game map with the specified dimensions
    public void initializeMap(List<String> lines) {
        for(String line: lines){
            try {
                String[] parts = line.split("-");
                switch (parts[0]){
                    case "C":
                        initializeMapDefault((Integer.parseInt(parts[1])), Integer.parseInt(parts[2]));
                        break;
                    case "M":
                        addMountain(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        break;
                    case "T":
                        addTreasure(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                        break;
                    case "A":
                        addAdventurer(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4].charAt(0), parts[5]);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.err.println("An error occurred while processing line: " + line + " - " + e.getMessage());
            }
        }
    }

    private void initializeMapDefault(int x, int y) {
        try {
            map = new Map(x, y);
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    map.getGrid()[i][j] = new Cell(i, j, TypeCell.PLAIN);
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while initializing the default map: " + e.getMessage());
        }
    }

    private void addAdventurer(String nameAdventurer, int horizontalX, int verticalY, char orientation, String movementSequence) {
        try {
            map.getAdventurers().add(new Adventurer(nameAdventurer, horizontalX, verticalY, orientation, movementSequence));
        } catch (Exception e) {
            System.err.println("An error occurred while adding adventurer: " + nameAdventurer + " - " + e.getMessage());
        }
    }

    // Add a mountain to the specified coordinates on the map
    public void addMountain(int horizontalX, int verticalY) {
        try {
            // Update the map to mark the specified grid cell as a mountain
            map.getGrid()[horizontalX][verticalY].setIsMountain(true);
            map.getGrid()[horizontalX][verticalY].setTypeCell(TypeCell.MOUNTAIN);
        } catch (Exception e) {
            System.err.println("An error occurred while adding mountain: " + e.getMessage());
        }
    }

    // Add a specified number of treasures to the specified coordinates on the map
    public void addTreasure(int horizontalX, int verticalY, int nbTreasure) {
        try {
            // Update the map to indicate the presence of treasures at the specified grid cell
            map.getGrid()[horizontalX][verticalY].setTypeCell(TypeCell.TREASURE);
            map.getGrid()[horizontalX][verticalY].setNbTreasure(nbTreasure);
        } catch (Exception e) {
            System.err.println("An error occurred while adding treasure: " + e.getMessage());
        }
    }

    // Print the map grid with adventurers and treasures
    public void printMap() {
        try {
            Cell[][] grid = map.getGrid();
            List<Adventurer> adventurers = map.getAdventurers();
            System.out.print("\n--TREASURE MAP--\n");
            for (int y = 0; y < map.getVerticalAxis(); y++) {
                for (int x = 0; x < map.getHorizontalAxis(); x++) {
                    Cell cell = grid[x][y];
                    // Check if adventurer is on this cell
                    boolean adventurerPresent = false;
                    for (Adventurer adventurer : adventurers) {
                        if (adventurer.getX() == x && adventurer.getY() == y) {
                            System.out.print(adventurer.getName().charAt(0) + "(" + adventurer.getTreasureCount() + ")");
                            adventurerPresent = true;
                            break;
                        }
                    }
                    if (!adventurerPresent) {
                        // Check cell type and print accordingly
                        switch (cell.getTypeCell()) {
                            case PLAIN:
                                System.out.print(".");
                                break;
                            case MOUNTAIN:
                                System.out.print("M");
                                break;
                            case TREASURE:
                                System.out.print("T(" + cell.getNbTreasure() + ")");
                                break;
                        }
                    }
                    System.out.print("\t\t\t");
                }
                System.out.println(); // Move to next row
            }
        } catch (Exception e) {
            System.err.println("An error occurred while printing the map: " + e.getMessage());
        }
    }

    // Get the current state of the map
    public Map getMap() {
        return map;
    }

    // Get the horizontal size of the map
    public int getMapHorizontalSize() {
        return map.getHorizontalAxis();
    }

    // Get the vertical size of the map
    public int getMapVerticalSize() {
        return map.getVerticalAxis();
    }

    // Get the count of treasures at the specified coordinates on the map
    public int getTreasureCount(int horizontalX, int verticalY) {
        return map.getGrid()[horizontalX][verticalY].getNbTreasure();
    }
}
