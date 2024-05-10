package main.java.com.carbonit.game.model;

public class Adventurer {
    private final String name;
    private int x;
    private int y;
    private char orientation;
    private int treasureCount;
    private final String movementSequence;

    public Adventurer(String name, int x, int y, char orientation, String movementSequence) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.treasureCount = 0;
        this.movementSequence = movementSequence;
    }

    public char getOrientation(){
        return this.orientation;
    }

    public void turnLeft(){
        switch (this.orientation) {
            case 'N':
                this.orientation = 'W';
                break;
            case 'S':
                this.orientation = 'E';
                break;
            case 'E':
                this.orientation = 'N';
                break;
            case 'W':
                this.orientation = 'S';
                break;
            default:
                break;
        }
    }

    // Turn right
    public void turnRight() {
        switch (this.orientation) {
            case 'N':
                this.orientation = 'E';
                break;
            case 'S':
                this.orientation = 'W';
                break;
            case 'E':
                this.orientation = 'S';
                break;
            case 'W':
                this.orientation = 'N';
                break;
            default:
                break;
        }
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public String getName() {
        return name;
    }

    public String getMovementSequence() {
        return this.movementSequence;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void incrementTreasureCount() {
        this.treasureCount++;
    }
}
