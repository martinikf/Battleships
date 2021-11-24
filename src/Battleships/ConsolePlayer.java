package Battleships;

public class ConsolePlayer extends Player{

    public ConsolePlayer(String name){
        super(name);
    }

    @Override
    public boolean placeShip(Ship ship, int row, int col) {
        return false;
    }

    @Override
    public boolean shoot(int row, int col) {
        return false;
    }
}
