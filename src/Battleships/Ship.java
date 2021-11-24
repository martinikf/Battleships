package Battleships;

public abstract class Ship {

    protected Position[] occupies;      //Souřadnice na kterých je loď umístěná
    protected byte rotation;
    protected Position start;
    protected int size;

    public Ship(int row, int col, byte rotation, int size){
        start = new Position(row, col);
        this.rotation = rotation;
        this.size = size;
        createOccupies();
    }

    protected abstract boolean createOccupies();

    public Position[] getOccupies() { return occupies; }
    public Position getStart(){ return start;}
    public byte getRotation(){return rotation;}
}
