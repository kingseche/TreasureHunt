package main.java.com.carbonit.game.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderService {
    //Reads the input file and returns its contents as a list of strings.
    public List<String> readInputFile(String filePath) throws IOException {
        // Open the input file for reading
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    .filter(line -> !line.startsWith("#"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the input file: " + e.getMessage());
            throw e;
        } catch (NumberFormatException e) {
            throw new IOException("Invalid map dimensions. Make sure the first line of the input file has the correct format: C-{width}-{height}");
        }
    }
}
