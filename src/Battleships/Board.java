package Battleships;

import java.util.ArrayList;

public class Board {

    private final Tile[][] board;
    private final ArrayList<Ship> shipsOnBoard;

    public Board(){
        board = createBoard();
        shipsOnBoard = new ArrayList<>();
    }

    private Tile[][] createBoard(){
        var b = new Tile[Game.BOARD_HEIGHT][Game.BOARD_WIDTH];

        for (int i = 0; i < Game.BOARD_HEIGHT; i++){
            for(int j = 0; j < Game.BOARD_WIDTH; j++){
                b[i][j] = Tile.Water;
            }
        }
        return b;
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

    public void printBoard(){
        for (int i = 0; i < Game.BOARD_HEIGHT; i++){
            for(int j = 0; j < Game.BOARD_WIDTH; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}