package Battleships;

public abstract class Player {

    protected Board board;
    protected String name;

    public Player(String name){
        board = new Board();
        this.name = name;
    }

    public abstract boolean placeShip(Ship ship, int row, int col);

    public abstract boolean shoot(int row, int col);

}
