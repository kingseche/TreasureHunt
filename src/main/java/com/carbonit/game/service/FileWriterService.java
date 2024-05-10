package main.java.com.carbonit.game.service;

import main.java.com.carbonit.game.model.Adventurer;
import main.java.com.carbonit.game.model.Map;
import main.java.com.carbonit.game.model.TypeCell;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriterService {
    // Method to write the final state of the map and adventurers to a file
    public void writeOutputFile(String filePath, Map map) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            // Write map dimensions
            writer.write("C - " + map.getHorizontalAxis() + " - " + map.getVerticalAxis());
            writer.newLine();

            // Write mountains
            writeMountains(writer, map);

            // Write treasures
            writeTreasures(writer, map);

            // Write adventurers
            writeAdventurers(writer, map);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the output file: " + e.getMessage());
            throw e;
        }
    }

    // Method to write mountains to the file
    private void writeMountains(BufferedWriter writer, Map map) throws IOException {
        for (int x = 0; x < map.getHorizontalAxis(); x++) {
            for (int y = 0; y < map.getVerticalAxis(); y++) {
                if (map.getTypeCellInGrid(x, y) == TypeCell.MOUNTAIN) {
                    writer.write("M - " + x + " - " + y);
                    writer.newLine();
                }
            }
        }
    }

    // Method to write treasures to the file
    private void writeTreasures(BufferedWriter writer, Map map) throws IOException {
        writer.write("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}");
        writer.newLine();
        for (int x = 0; x < map.getHorizontalAxis(); x++) {
            for (int y = 0; y < map.getVerticalAxis(); y++) {
                int treasureCount = map.getGrid()[x][y].getNbTreasure();
                if (treasureCount > 0) {
                    writer.write("T - " + x + " - " + y + " - " + treasureCount);
                    writer.newLine();
                }
            }
        }
    }

    // Method to write adventurers to the file
    private void writeAdventurers(BufferedWriter writer, Map map) throws IOException {
        writer.write("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}");
        writer.newLine();
        List<Adventurer> adventurers = map.getAdventurers();
        for (Adventurer adventurer : adventurers) {
            writer.write("A - " + adventurer.getName() + " - " + adventurer.getX() + " - "
                    + adventurer.getY() + " - " + adventurer.getOrientation() + " - "
                    + adventurer.getTreasureCount());
            writer.newLine();
        }
    }
}
