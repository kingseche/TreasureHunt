package main.java.com.carbonit.game.model;

public class Cell {
    private final int posX;
    private final int posY;
    private TypeCell typeCell;
    private boolean isMountain;
    private int nbTreasure;

    public Cell (int posX, int posY, TypeCell typeCell){
        this.posX = posX;
        this.posY = posY;
        this.typeCell = typeCell;
    }

    public void setTypeCell(TypeCell typeCell) {
        this.typeCell = typeCell;
    }

    public TypeCell getTypeCell(){
        return this.typeCell;
    }

    public void setIsMountain(boolean isMountain) {
        this. isMountain = isMountain;
    }

    public void setNbTreasure(int nbTreasure) {
        this.nbTreasure = nbTreasure;
    }

    public int getNbTreasure(){
        return this.nbTreasure;
    }
}
