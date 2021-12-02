package Battleships;

import java.util.ArrayList;

public class Board {

    private final Tile[][] board;
    private final ArrayList<Ship> shipsOnBoard;
    private final int shipsCount;
    private final int height;
    private final int width;

    public Board(int height, int width, int ships){
        this.shipsCount = ships;
        this.height = height;
        this.width = width;
        board = createBoard();
        shipsOnBoard = new ArrayList<>();
    }

    private Tile[][] createBoard(){
        var b = new Tile[height][width];

        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
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
            for(var position : ship.getParts()){
                board[position.getRow()][position.getCol()] = Tile.Ship;
            }
            shipsOnBoard.add(ship);
            return true;
        }
        return false;
    }

    public ShootResult shoot(int row, int col) {
        if(coordsOutOfBounds(row, col)){
            return ShootResult.Fault;
        }

        switch (board[row][col]){
            case Water:
                board[row][col] = Tile.Miss;
                return ShootResult.Miss;
            case Ship:
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
        return row < 0 || row >= getHeight() || col < 0 || col >= getWidth();
    }

    private boolean canBePlaced(Ship ship) {
        for(var position : ship.getParts()){
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

    private boolean isWater(ShipPart position, int i, int i1) {
        return !coordsOutOfBounds(position.getRow() + i, position.getCol() + i1)
        && board[position.getRow()+i][position.getCol()+i1] == Tile.Water;
    }

    private boolean shipOnPosition(ShipPart pos, int i1, int i2){
        return !coordsOutOfBounds(pos.getRow() + i1, pos.getCol() + i2)
                && board[pos.getRow() + i1][pos.getCol() + i2] != Tile.Water;
    }

    public void printBoardPlayersPerspective(){
        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
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
        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getShipsCount() {
        return shipsCount;
    }
}