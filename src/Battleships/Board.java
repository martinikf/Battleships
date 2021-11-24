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

    public boolean placeShip(Ship ship){
        if(ship == null){
            return false;
        }
        else if(canBePlaced(ship)){
            for(var position : ship.getOccupies()){
                board[position.getRow()][position.getCol()] = Tile.Ship;
            }
            shipsOnBoard.add(ship);
            return true;
        }
        return false;
    }

    //Returns true if every boat was destroyed
    public ShootResult shoot(int row, int col) {
        if(!coordsInBounds(row, col)){
            return ShootResult.Fault; //opravit
        }

        switch (board[row][col]){
            case Water:
                board[row][col] = Tile.Miss;
                return ShootResult.Miss;
            case Ship:
                board[row][col] = Tile.Hit;
                hit(row, col);
                return checkWin();
            case Miss:
            case Hit:
            default:
                return ShootResult.Fault;
        }
    }

    private ShootResult checkWin() {
        for(var ship : shipsOnBoard){
            if(!ship.isDestroyed()){
                return ShootResult.Hit;
            }
        }
        return ShootResult.Win;
    }

    private void hit(int row, int col) {
        for(var ship : shipsOnBoard){
            int index = -1;
            if(ship.isOnCoords(row, col)){
                if(ship.hit(row, col)){
                    System.out.println("Ship destroyed!" + ship);
                }
            }
        }
    }

    private boolean coordsInBounds(int row, int col){
        if(row < 0 || row >= Game.BOARD_HEIGHT || col < 0 || col >= Game.BOARD_WIDTH){
            return false;
        }
        else{
            return true;
        }
    }

    //Přidat kontrolu pro lod v okolí
    private boolean canBePlaced(Ship ship) {
        for(var position : ship.getOccupies()){
            if(!coordsInBounds(position.getRow(), position.getCol())){
                return false;
            }
            if(board[position.getRow()][position.getCol()] != Tile.Water)
                return false;
        }
        return true;
    }

    public void printBoardPlayersPerspective(){
        for (int i = 0; i < Game.BOARD_HEIGHT; i++){
            for(int j = 0; j < Game.BOARD_WIDTH; j++){
                switch (board[i][j]){
                    case Water -> System.out.print("-");
                    case Ship -> System.out.print("S");
                    case Hit -> System.out.print("X");
                    case Miss -> System.out.print("O");
                }
                System.out.print(" | ");
            }
            System.out.print(System.lineSeparator());
        }
    }

    public void printBoardOpponentsPerspective(){
        for (int i = 0; i < Game.BOARD_HEIGHT; i++){
            for(int j = 0; j < Game.BOARD_WIDTH; j++){
                switch (board[i][j]){
                    case Water -> System.out.print("-");
                    case Ship -> System.out.print("-");
                    case Hit -> System.out.print("X");
                    case Miss -> System.out.print("O");
                }
                System.out.print(" | ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}