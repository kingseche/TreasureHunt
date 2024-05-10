package main.java.com.carbonit.game.model;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int horizontalAxis;
    private final int verticalAxis;
    private Cell[][] grid;
    private List<Adventurer> adventurers;

    public Map(int horizontalAxis, int verticalAxis) {
        // Initialize the grid with dimensions width x height
        this.horizontalAxis = horizontalAxis;
        this.verticalAxis = verticalAxis;
        this.grid = new Cell[horizontalAxis][verticalAxis];
        this.adventurers = new ArrayList<>();
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public int getHorizontalAxis() {
        return horizontalAxis;
    }

    public int getVerticalAxis() {
        return verticalAxis;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setAdventurers(List<Adventurer> adventurers){
        this.adventurers = adventurers;
    }

    public TypeCell getTypeCellInGrid(int x, int y){
        return this.grid[x][y].getTypeCell();
    }
}
