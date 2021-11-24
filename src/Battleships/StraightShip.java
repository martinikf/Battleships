package Battleships;

public class StraightShip extends Ship{

    private final int length;

    public StraightShip(int row, int col, int length){
        super(row, col);
        this.length = length;
        occupies = new Position[getLength()];
        occupies[0] = start;
    }

    public int getLength() {
        return length;
    }
}
