package test.java.com.carbonit.game.service;

import main.java.com.carbonit.game.model.TypeCell;
import main.java.com.carbonit.game.service.MapService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MapServiceTest {

    private MapService mapService;

    @BeforeEach
    void setUp() {
        // Sample map input for testing
        List<String> mapInputTest = Arrays.asList(
                "C-5-5",
                "M-1-1",
                "M-2-2",
                "T-0-3-1",
                "T-1-3-1",
                "T-3-4-2",
                "A-Lara-1-2-S-AADADA",
                "A-Croft-2-1-E-AADADA"
        );
        mapService = new MapService(mapInputTest);
    }

    @Test
    void testMapInitialization() {
        Assertions.assertEquals(5, mapService.getMapHorizontalSize());
        Assertions.assertEquals(5, mapService.getMapVerticalSize());
        Assertions.assertEquals(TypeCell.MOUNTAIN, mapService.getMap().getGrid()[1][1].getTypeCell());
        Assertions.assertEquals(TypeCell.MOUNTAIN, mapService.getMap().getGrid()[2][2].getTypeCell());
        Assertions.assertEquals(TypeCell.TREASURE, mapService.getMap().getGrid()[0][3].getTypeCell());
        Assertions.assertEquals(1, mapService.getTreasureCount(0, 3));
        Assertions.assertEquals(TypeCell.TREASURE, mapService.getMap().getGrid()[1][3].getTypeCell());
        Assertions.assertEquals(1, mapService.getTreasureCount(1, 3));
        Assertions.assertEquals(TypeCell.TREASURE, mapService.getMap().getGrid()[3][4].getTypeCell());
        Assertions.assertEquals(2, mapService.getTreasureCount(3, 4));
        Assertions.assertEquals(2, mapService.getMap().getAdventurers().size());
        Assertions.assertEquals("Lara", mapService.getMap().getAdventurers().getFirst().getName());
        Assertions.assertEquals(1, mapService.getMap().getAdventurers().getFirst().getX());
        Assertions.assertEquals(2, mapService.getMap().getAdventurers().getFirst().getY());
        Assertions.assertEquals('S', mapService.getMap().getAdventurers().get(0).getOrientation());
        Assertions.assertEquals("AADADA", mapService.getMap().getAdventurers().get(0).getMovementSequence());
        Assertions.assertEquals("Croft", mapService.getMap().getAdventurers().get(1).getName());
        Assertions.assertEquals(2, mapService.getMap().getAdventurers().get(1).getX());
        Assertions.assertEquals(1, mapService.getMap().getAdventurers().get(1).getY());
        Assertions.assertEquals('E', mapService.getMap().getAdventurers().get(1).getOrientation());
        Assertions.assertEquals("AADADA", mapService.getMap().getAdventurers().get(1).getMovementSequence());
    }
}
