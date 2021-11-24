package Battleships;

public abstract class Ship {

    protected Position[] occupies;      //Souřadnice na kterých je loď umístěná
    protected byte rotation;
    protected Position start;

    public Ship(int row, int col, byte rotation){
        start = new Position(row, col);
        this.rotation = rotation;
        createOccupies();
    }

    protected abstract boolean createOccupies();

    public Position[] getOccupies() { return occupies; }
    public Position getStart(){ return start;}
    public byte getRotation(){return rotation;}
}
