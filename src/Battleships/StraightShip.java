package Battleships;

public class StraightShip extends Ship{

    public StraightShip(int row, int col, byte rotation, int length) {
        super(row, col, rotation, length);
    }

    public int getLength() {
        return size;
    }

    @Override
    protected void createParts() {
        parts = new ShipPart[getLength()];
        parts[0] = start;

        for(int i = 1; i < getLength(); i++){
            if(rotation == 0){
                parts[i] = new ShipPart(getStart().getRow(), getStart().getCol() + i);
            }
            else{
                parts[i] = new ShipPart(getStart().getRow() + i, getStart().getCol());
            }
        }
    }
}