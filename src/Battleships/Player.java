package Battleships;

import java.util.Random;

public abstract class Player {

    protected Board board;
    protected String name;
    protected boolean isLocalPlayer;

    public Player(String name, Board board){
        this.board = board;
        this.name = name;
    }

    public abstract void placeShips();

    public Board getBoard(){return board;}
    public String getName(){return name;}
    public Boolean getIsLocalPlayer(){return isLocalPlayer;}

    public abstract Coordinates getShootCoords();

    //TODO opravit někdy crashne indexoutofbounds
    protected void placeBoatsRandomly(){
        Random rand = new Random();
        int boatsPlaced = 0;

        while (boatsPlaced < getBoard().getShipsCount()) {
            int row = rand.nextInt(getBoard().getHeight());
            int col = rand.nextInt(getBoard().getWidth());
            byte rotation = (byte) rand.nextInt(2);

            if (getBoard().placeShip(new StraightShip(row, col, rotation, boatsPlaced + 1))) {
                boatsPlaced++;
            }
        }}
}