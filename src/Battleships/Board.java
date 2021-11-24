package Battleships;

import java.util.ArrayList;

public class Board {

    private final Tile[][] board;
    private final ArrayList<Ship> shipsOnBoard;

    public Board(){
        board = new Tile[Game.BOARD_HEIGHT][Game.BOARD_WIDTH];
        shipsOnBoard = new ArrayList<>();
    }

    public Tile[][] getBoard() {
        return board;
    }

    public ArrayList<Ship> getShipsOnBoard() {
        return shipsOnBoard;
    }

    public boolean placeShip(Ship ship, int row, int col){
        if(ship == null || row >= Game.BOARD_HEIGHT || row < 0 || col >= Game.BOARD_WIDTH || col < 0){
            return false;
        }

        return false;
    }
}
