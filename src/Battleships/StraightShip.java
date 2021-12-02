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
        shipParts = new ShipPart[getLength()];
        shipParts[0] = start;

        for(int i = 1; i < getLength(); i++){
            if(rotation == 0){
                shipParts[i] = new ShipPart(getStart().getRow(), getStart().getCol() + i);
            }
            else{
                shipParts[i] = new ShipPart(getStart().getRow() + i, getStart().getCol());
            }
        }
    }
}