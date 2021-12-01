package Battleships;

import java.util.Random;

public class RandomComputerPlayer extends ComputerPlayer{

    Random rand = new Random();

    public RandomComputerPlayer(String name){
        super(name);
    }

    @Override
    public void placeShips() {
        placeBoatsRandomly();
    }

    @Override
    public Coordinates getShootCoords() {
        return new Coordinates(rand.nextInt(Game.BOARD_HEIGHT), rand.nextInt(Game.BOARD_WIDTH));
    }
}