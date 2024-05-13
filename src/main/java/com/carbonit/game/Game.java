package main.java.com.carbonit.game;
import main.java.com.carbonit.game.service.FileReaderService;
import main.java.com.carbonit.game.service.FileWriterService;
import main.java.com.carbonit.game.service.MapService;
import main.java.com.carbonit.game.service.MovemetService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static void main (String[] args) throws IOException {
        // Initialize scanner to read user input
        try (Scanner scanner = new Scanner(System.in)) {
            String filePath;
            // Prompt user to enter the input file name
            System.out.println("Enter the name of the input file (e.g., input.txt):");
            String fileName = scanner.nextLine();

            // Read input file based on user input
            filePath = "resources/" + fileName;
            FileReaderService fileReaderService = new FileReaderService();
            List<String> lines = fileReaderService.readInputFile(filePath);

            //Check if file is empty
            if (!lines.isEmpty()) {
                lines.forEach(System.out::println);

                // Initialize map service with input file contents
                MapService mapService = new MapService(lines);
                System.out.println(
                        "Map Width (Horizontal) : " + mapService.getMapHorizontalSize() +
                                "\nMap Height (Vertical) : " + mapService.getMapVerticalSize()
                );

                // Print initial map state
                mapService.printMap();

                // Simulate adventurer movements
                MovemetService movemetService = new MovemetService();
                movemetService.simulateMovement(mapService.getMap());

                // Print final map state
                mapService.printMap();

                // Write final map state to output file
                FileWriterService fileWriterService = new FileWriterService();
                fileWriterService.writeOutputFile("resources/output.txt", mapService.getMap());
            } else {
                System.out.println("Error: The specified file is empty . Please try again with the right file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The specified file does not exist.");
        } catch (IOException e) {
            System.out.println("Error: An I/O exception occurred while reading the file.");
        }
    }
}
