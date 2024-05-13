package main.java.com.carbonit.game.service;

import main.java.com.carbonit.game.model.Adventurer;
import main.java.com.carbonit.game.model.Cell;
import main.java.com.carbonit.game.model.Map;
import main.java.com.carbonit.game.model.TypeCell;

import java.util.List;

public class MovemetService {

    // Simulate movement for all adventurers on the map
    public void simulateMovement(Map map) {
        List<Adventurer> adventurers = map.getAdventurers();

        for (Adventurer adventurer : adventurers) {
            String movementSequence = adventurer.getMovementSequence();
            for (char movement : movementSequence.toCharArray()) {
                System.out.print(movement);
                try {
                    if (movement == 'G') {
                        // Turn left
                        adventurer.turnLeft();
                    } else if (movement == 'D') {
                        // Turn right
                        adventurer.turnRight();
                    } else if (movement == 'A') {
                        // Move forward
                        int newX = adventurer.getX();
                        int newY = adventurer.getY();

                        switch (adventurer.getOrientation()) {
                            case 'N':
                                newY--;
                                break;
                            case 'S':
                                newY++;
                                break;
                            case 'W':
                                newX--;
                                break;
                            case 'E':
                                newX++;
                                break;
                        }

                        // Check if new position is within map boundaries
                        if (isWithinMapBounds(map, newX, newY)) {
                            if (!isMountainCell(map, newX, newY)) {
                                // Update adventurer's position
                                adventurer.setX(newX);
                                adventurer.setY(newY);

                                // Check for treasure
                                TypeCell typeCell = map.getTypeCellInGrid(newX, newY);
                                if (typeCell == TypeCell.TREASURE) {
                                    // Collect treasure
                                    collectTreasure(map, adventurer, newX, newY);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("An error occurred while simulating movement: " + e.getMessage());
                }
            }
            System.out.println("\nOrientation: " + adventurer.getOrientation() + "\nPosition X : " + adventurer.getX() + " Position Y : " + adventurer.getY() + "\nNbTreasure ; " + adventurer.getTreasureCount() + "\n");
        }
    }

    // Check if position is within map boundaries
    private boolean isWithinMapBounds(Map map, int x, int y) {
        return x >= 0 && x < map.getHorizontalAxis() && y >= 0 && y < map.getVerticalAxis();
    }

    // Check if destination position is a MOUNTAIN
    private boolean isMountainCell(Map map, int x, int y) {
        try {
            return map.getGrid()[x][y].getTypeCell() == TypeCell.MOUNTAIN;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Coordinates are out of bounds: " + e.getMessage());
            return false;
        }
    }

    // Collect treasure from the cell
    private void collectTreasure(Map map, Adventurer adventurer, int x, int y) {
        try {
            Cell cell = map.getGrid()[x][y];
            int treasureCount = cell.getNbTreasure();
            if (treasureCount > 0) {
                adventurer.incrementTreasureCount();
                cell.setNbTreasure(treasureCount - 1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Coordinates are out of bounds: " + e.getMessage());
        }
    }
}