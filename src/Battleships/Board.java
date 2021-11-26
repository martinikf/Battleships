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

    public ShootResult shoot(int row, int col) {
        if(coordsOutOfBounds(row, col)){
            return ShootResult.Fault; //opravit ?
        }

        switch (board[row][col]){
            case Water:
                board[row][col] = Tile.Miss;
                return ShootResult.Miss;
            case Ship: //destroyed obsahuje zprávu o zničené lodi
                board[row][col] = Tile.Hit;
                String destroyed;
                if((destroyed = hit(row, col)) != null){
                    System.out.println(destroyed);
                    if(checkWin() == ShootResult.Win){
                        return ShootResult.Win;
                    }
                    return ShootResult.Destroyed;
                }
                return ShootResult.Hit;
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

    private String hit(int row, int col) {
        for(var ship : shipsOnBoard){
            if(ship.isOnCoords(row, col)){
                if(ship.hit(row, col)){
                    return ship.destroyMessage();
                }
            }
        }
        return null;
    }

    private boolean coordsOutOfBounds(int row, int col){
        return row < 0 || row >= Game.BOARD_HEIGHT || col < 0 || col >= Game.BOARD_WIDTH;
    }

    private boolean canBePlaced(Ship ship) {
        for(var position : ship.getOccupies()){
            if(     shipOnPosition(position, -1, -1)||
                    shipOnPosition(position, -1, 0) ||
                    shipOnPosition(position, -1, 1) ||
                    shipOnPosition(position, 0, -1) ||
                    !isWater(position, 0, 0)  ||
                    shipOnPosition(position, 0, 1)  ||
                    shipOnPosition(position, 1, -1) ||
                    shipOnPosition(position, 1, 0)  ||
                    shipOnPosition(position, 1, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWater(Position position, int i, int i1) {
        return !coordsOutOfBounds(position.getRow() + i, position.getCol() + i1)
        && board[position.getRow()+i][position.getCol()+i1] == Tile.Water;
    }

    private boolean shipOnPosition(Position pos, int i1, int i2){
        return !coordsOutOfBounds(pos.getRow() + i1, pos.getCol() + i2)
                && board[pos.getRow() + i1][pos.getCol() + i2] != Tile.Water;
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
                    case Water, Ship -> System.out.print("-");
                    case Hit -> System.out.print("X");
                    case Miss -> System.out.print("O");
                }
                System.out.print(" | ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}