package Battleships;

import java.util.Random;

public class RandomComputerPlayer extends ComputerPlayer{

    Random rand = new Random();

    public RandomComputerPlayer(String name, Board board){
        super(name, board);
    }

    @Override
    public void placeShips() {
        placeBoatsRandomly();
    }

    @Override
    public Coordinates getShootCoords() {
        return new Coordinates(rand.nextInt(getBoard().getHeight()), rand.nextInt(getBoard().getWidth()));
    }
}