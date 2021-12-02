package Battleships;

public class ShipPart {

    private final Coordinates position;
    private boolean hit = false;

    public ShipPart(int row, int col){
        position = new Coordinates(row, col);
    }

    public int getRow() {
        return position.row();
    }

    public int getCol() {
        return position.col();
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}