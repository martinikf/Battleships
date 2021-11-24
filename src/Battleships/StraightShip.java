package Battleships;

import jdk.jshell.spi.ExecutionControl;

public class StraightShip extends Ship{

    private final int length;

    public StraightShip(int row, int col, byte rotation, int length) throws ExecutionControl.NotImplementedException {
        super(row, col, rotation);
        this.length = length;
        if(!createOccupies()){
            throw new ExecutionControl.NotImplementedException("Lod nelze umístit");
        }
    }

    public int getLength() {
        return length;
    }

    @Override
    protected boolean createOccupies() {
        occupies = new Position[getLength()];
        occupies[0] = start;

        for(int i = 1; i < getLength(); i++){
            if(rotation == 0){
                occupies[i] = new Position(getStart().getRow(), getStart().getCol() + i);
            }

            else if(rotation == 1){
                occupies[i] = new Position(getStart().getRow() + i, getStart().getCol());
            }
        }
        return true;
    }
}
