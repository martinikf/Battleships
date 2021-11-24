package Battleships;

import java.util.Random;

public class RandomComputerPlayer extends ComputerPlayer{

    Random rand = new Random();

    public RandomComputerPlayer(String name){
        super(name);
    }

    @Override
    public void placeBoats() {
        int boatsPlaced = 0;
        while (boatsPlaced != Game.BOATS_COUNT) {
            int row = rand.nextInt(Game.BOARD_HEIGHT);
            int col = rand.nextInt(Game.BOARD_WIDTH);
            byte rotation = (byte) rand.nextInt(2);

            if (board.placeShip(new StraightShip(row, col, rotation, boatsPlaced + 1))) {
                boatsPlaced++;
            }
        }
    }

    @Override
    public Coordinates getShootCoords() {
        return new Coordinates(rand.nextInt(Game.BOARD_HEIGHT), rand.nextInt(Game.BOARD_WIDTH));
    }
}