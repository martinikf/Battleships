package Battleships;

public class StraightShip extends Ship{

    public StraightShip(int row, int col, byte rotation, int length) {
        super(row, col, rotation, length);
    }

    public int getLength() {
        return size;
    }

    @Override
    protected boolean createOccupies() {
        occupies = new Position[getLength()];
        occupies[0] = start;

        for(int i = 1; i < getLength(); i++){
            if(rotation == 0){
                occupies[i] = new Position(getStart().getRow(), getStart().getCol() + i);
            }
            else{
                occupies[i] = new Position(getStart().getRow() + i, getStart().getCol());
            }
        }
        return true;
    }
}