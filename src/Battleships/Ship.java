package Battleships;

public abstract class Ship {

    protected ShipPart[] shipParts;
    protected final byte rotation;
    protected final ShipPart start;
    protected final int size;
    protected boolean destroyed = false;

    public Ship(int row, int col, byte rotation, int size) {
        start = new ShipPart(row, col);
        this.rotation = rotation;
        this.size = size;

        createParts();
    }

    public boolean isOnCoords(int row, int col) {
        for (var position : getShipParts()) {
            if (position.getRow() == row && position.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private int indexFromCoords(int row, int col) {
        for (var i = 0; i < size; i++) {
            if (shipParts[i].getRow() == row && shipParts[i].getCol() == col) {
                return i;
            }
        }
        return -1;
    }

    //Returns true if the hit destroyed this ship
    public boolean hit(int row, int col) {
        getShipParts()[indexFromCoords(row, col)].setHit(true);
        for (var position : getShipParts()) {
            if (!position.isHit()) {
                return false;
            }
        }
        destroyed = true;
        return true;
    }

    protected abstract void createParts();

    public ShipPart[] getShipParts() {
        return shipParts;
    }

    public ShipPart getStart() {
        return start;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public String destroyMessage() {
        return this + " has been destroyed!";
    }
}