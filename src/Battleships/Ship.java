package Battleships;

public abstract class Ship {

    protected Position[] occupies;      //Souřadnice na kterých je loď umístěná
    protected byte rotation;
    protected Position start;

    public Ship(int row, int col){
        start = new Position(row, col);
    }
}
