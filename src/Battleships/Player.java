package Battleships;

import java.util.Random;

public abstract class Player {

    protected Board board;
    protected String name;

    public Player(String name){
        board = new Board();
        this.name = name;
    }

    public abstract void placeBoats();

    public Board getBoard(){return board;}
    public String getName(){return name;}

    public abstract Coordinates getShootCoords();

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