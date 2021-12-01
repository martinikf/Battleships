package Battleships;

import java.util.Random;

public abstract class Player {

    protected Board board;
    protected String name;
    protected boolean isLocalPlayer;

    public Player(String name){
        board = new Board();
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

        while (boatsPlaced < Game.BOATS_COUNT) {
            int row = rand.nextInt(Game.BOARD_HEIGHT);
            int col = rand.nextInt(Game.BOARD_WIDTH);
            byte rotation = (byte) rand.nextInt(2);

            if (getBoard().placeShip(new StraightShip(row, col, rotation, boatsPlaced + 1))) {
                boatsPlaced++;
            }
        }}
}