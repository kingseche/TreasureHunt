package test.java.com.carbonit.game.service;

import main.java.com.carbonit.game.model.Adventurer;
import main.java.com.carbonit.game.model.Cell;
import main.java.com.carbonit.game.model.Map;
import main.java.com.carbonit.game.model.TypeCell;
import main.java.com.carbonit.game.service.FileWriterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriterServiceTest {
    private FileWriterService fileWriterService;
    private Map map;

    @BeforeEach
    void setUp() {
        fileWriterService = new FileWriterService();
        map = new Map(5, 5);
        initializeMapGrid();
        // Add mountains
        map.getGrid()[1][1].setTypeCell(TypeCell.MOUNTAIN);
        map.getGrid()[2][2].setTypeCell(TypeCell.MOUNTAIN);
        // Add treasures
        map.getGrid()[0][3].setTypeCell(TypeCell.TREASURE);
        map.getGrid()[0][3].setNbTreasure(1);
        map.getGrid()[1][3].setTypeCell(TypeCell.TREASURE);
        map.getGrid()[1][3].setNbTreasure(1);
        map.getGrid()[3][4].setTypeCell(TypeCell.TREASURE);
        map.getGrid()[3][4].setNbTreasure(2);
        // Add adventurers
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("Lara", 1, 2, 'S', "AADADA"));
        adventurers.add(new Adventurer("Croft", 2, 1, 'E', "AADADA"));
        map.setAdventurers(adventurers);
    }

    @Test
    void testWriteOutputFile() throws IOException {
        String outputFile = "resources/testOutput.txt";
        fileWriterService.writeOutputFile(outputFile, map);

        // Read the content of the written file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Validate the content of the file
        Assertions.assertEquals("C - 5 - 5", lines.get(0));
        Assertions.assertEquals("M - 1 - 1", lines.get(1));
        Assertions.assertEquals("M - 2 - 2", lines.get(2));
        Assertions.assertEquals("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}", lines.get(3));
        Assertions.assertEquals("T - 0 - 3 - 1", lines.get(4));
        Assertions.assertEquals("T - 1 - 3 - 1", lines.get(5));
        Assertions.assertEquals("T - 3 - 4 - 2", lines.get(6));
        Assertions.assertEquals("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}", lines.get(7));
        Assertions.assertEquals("A - Lara - 1 - 2 - S - 0", lines.get(8));
        Assertions.assertEquals("A - Croft - 2 - 1 - E - 0", lines.get(9));
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
}
