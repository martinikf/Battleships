package Battleships;

public abstract class Ship {

    protected Position[] occupies;      //Souřadnice na kterých je loď umístěná
    protected byte rotation;
    protected Position start;
    protected int size;
    protected boolean destroyed = false;

    public Ship(int row, int col, byte rotation, int size){
        start = new Position(row, col);
        this.rotation = rotation;
        this.size = size;

        createOccupies();
    }

    public boolean isOnCoords(int row, int col){
        for(var position : getOccupies()){
            if(position.getRow() == row && position.getCol() == col){
                return true;
            }
        }
        return false;
    }

    private int indexOfCoords(int row, int col){
        for(int i = 0; i < size; i++){
            if(occupies[i].getRow() == row && occupies[i].getCol() == col){
                return i;
            }
        }
        return -1;
    }

    //Returns true if the hit destroyed this boat
    public boolean hit(int row, int col){
        getOccupies()[indexOfCoords(row, col)].setHit(true);
        for(var position : getOccupies()){
            if(!position.isHit()){
                return false;
            }
        }
        destroyed = true;
        return true;
    }

    protected abstract void createOccupies();

    public Position[] getOccupies() { return occupies; }
    public Position getStart(){ return start; }
    public byte getRotation(){return rotation; }
    public boolean isDestroyed(){return destroyed; }

    public String destroyMessage() {
        return this + "has been destroyed!";
    }
}