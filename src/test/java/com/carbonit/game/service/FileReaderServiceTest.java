package test.java.com.carbonit.game.service;

import main.java.com.carbonit.game.service.FileReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class FileReaderServiceTest {
    private FileReaderService fileReaderService;

    @BeforeEach
    void setUp() {
        fileReaderService = new FileReaderService();
    }

    @Test
    void readInputFile_ValidFilePath_ReturnsList() {
        // Test reading a valid input file path
        String filePath = "resources/input.txt";
        try {
            List<String> lines = fileReaderService.readInputFile(filePath);
            long countC = lines.stream().filter(line -> line.startsWith("C")).count();
            Assertions.assertEquals(1, countC);
            Assertions.assertNotNull(lines);
            Assertions.assertFalse(lines.isEmpty());
        } catch (IOException e) {
            Assertions.fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    void readInputFile_InvalidFilePath_ThrowsIOException() {
        // Test reading an invalid input file path
        String filePath = "resources/wrongInputFile.txt";
        assertThrows(IOException.class, () -> {
            fileReaderService.readInputFile(filePath);
        });
    }

    @Test
    void readInputFile_MapFormatRightNumberOfVariables() {
        // Test that map lines have only two variables
        String filePath = "resources/input.txt";
        try {
            List<String> lines = fileReaderService.readInputFile(filePath);
            for (String line : lines) {
                String[] parts = line.split("-");
                switch (parts[0]){
                    case "C":
                        Assertions.assertEquals(3, parts.length, "Map line should have exactly two variables");
                        break;
                    case "M":
                        Assertions.assertEquals(3, parts.length, "Mountain lines should have exactly two variables");
                        break;
                    case "T":
                        Assertions.assertEquals(4, parts.length, "Treasure lines should have exactly three variables");
                        break;
                    case "A":
                        Assertions.assertEquals(6, parts.length, "Adventurer lines should have exactly five variables");
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            Assertions.fail("IOException occurred: " + e.getMessage());
        }
    }
}
