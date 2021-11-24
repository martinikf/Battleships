package Battleships;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col= col;
    }

    //ověřit aby to bylo v rozměrech boardy?
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}