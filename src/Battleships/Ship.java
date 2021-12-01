package Battleships;

public abstract class Ship {

    protected ShipPart[] parts;      //Souřadnice na kterých je loď umístěná
    protected byte rotation;
    protected ShipPart start;
    protected int size;
    protected boolean destroyed = false;

    public Ship(int row, int col, byte rotation, int size){
        start = new ShipPart(row, col);
        this.rotation = rotation;
        this.size = size;

        createParts();
    }

    public boolean isOnCoords(int row, int col){
        for(var position : getParts()){
            if(position.getRow() == row && position.getCol() == col){
                return true;
            }
        }
        return false;
    }

    private int indexOfCoords(int row, int col){
        for(int i = 0; i < size; i++){
            if(parts[i].getRow() == row && parts[i].getCol() == col){
                return i;
            }
        }
        return -1;
    }

    //Returns true if the hit destroyed this boat
    public boolean hit(int row, int col){
        getParts()[indexOfCoords(row, col)].setHit(true);
        for(var position : getParts()){
            if(!position.isHit()){
                return false;
            }
        }
        destroyed = true;
        return true;
    }

    protected abstract void createParts();

    public ShipPart[] getParts() { return parts; }
    public ShipPart getStart(){ return start; }
    public byte getRotation(){return rotation; }
    public boolean isDestroyed(){return destroyed; }

    public String destroyMessage() {
        return this + " has been destroyed!";
    }
}