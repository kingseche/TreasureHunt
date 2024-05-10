package test.java.com.carbonit.game.service;
import main.java.com.carbonit.game.model.Adventurer;
import main.java.com.carbonit.game.model.Cell;
import main.java.com.carbonit.game.model.Map;
import main.java.com.carbonit.game.model.TypeCell;
import main.java.com.carbonit.game.service.MovemetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovementServiceTest {
    private MovemetService movementService;
    private Map map;

    @BeforeEach
    public void setUp() {
        // Initialize the map and movement service for each test
        map = new Map(5, 5); // Adjust map dimensions as needed
        initializeMapGrid();
        movementService = new MovemetService();
    }

    private void initializeMapGrid() {
        // Initialize the map grid with Cell objects
        Cell[][] grid = new Cell[map.getHorizontalAxis()][map.getVerticalAxis()];
        for (int x = 0; x < map.getHorizontalAxis(); x++) {
            for (int y = 0; y < map.getVerticalAxis(); y++) {
                grid[x][y] = new Cell(x, y, TypeCell.PLAIN);
            }
        }
        map.setGrid(grid);
    }

    @Test
    public void testAdventurerMovementOutOfBounds() {
        // Create an adventurer with a movement sequence
        Adventurer adventurer = new Adventurer("TestAdventurer", 0, 0, 'N', "AADADA");
        map.getAdventurers().add(adventurer);

        // Simulate movement
        movementService.simulateMovement(map);

        // Assert the final position of the adventurer
        Assertions.assertEquals(1, adventurer.getX());
        Assertions.assertEquals(1, adventurer.getY());
    }

    @Test
    public void testMountainCollision() {
        // Place a mountain in the adventurer's path
        map.getGrid()[1][1].setTypeCell(TypeCell.MOUNTAIN);
        Adventurer adventurer = new Adventurer("TestAdventurer", 0, 1, 'E', "AAA");

        // Add the adventurer to the map
        map.getAdventurers().add(adventurer);

        // Simulate movement
        movementService.simulateMovement(map);

        // Assert that the adventurer remains in the same position
        Assertions.assertEquals(0, adventurer.getX());
        Assertions.assertEquals(1, adventurer.getY());
    }

    @Test
    public void testTreasureCollection() {
        // Place a treasure at the adventurer's destination
        map.getGrid()[1][1].setTypeCell(TypeCell.TREASURE);
        map.getGrid()[1][1].setNbTreasure(2);
        Adventurer adventurer = new Adventurer("TestAdventurer", 0, 1, 'E', "AADDAA");

        // Add the adventurer to the map
        map.getAdventurers().add(adventurer);

        // Simulate movement
        movementService.simulateMovement(map);

        // Assert that the adventurer collected the treasure
        Assertions.assertEquals(2, adventurer.getTreasureCount());
    }

    @Test
    public void testMultipleAdventurers() {
        // Create multiple adventurers with different movement sequences
        Adventurer adventurer1 = new Adventurer("TestAdventurer1", 0, 0, 'E', "AAAGGA");
        Adventurer adventurer2 = new Adventurer("TestAdventurer2", 4, 0, 'W', "AAADDAA");

        // Add adventurers to the map
        map.getAdventurers().add(adventurer1);
        map.getAdventurers().add(adventurer2);

        // Simulate movement
        movementService.simulateMovement(map);

        // Assert final positions of adventurers
        Assertions.assertEquals(2, adventurer1.getX());
        Assertions.assertEquals(0, adventurer1.getY());
        Assertions.assertEquals(3, adventurer2.getX());
        Assertions.assertEquals(0, adventurer2.getY());
    }
}
