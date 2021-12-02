package Battleships;

public class HackerComputerPlayer extends ComputerPlayer {

    private final Player opponent;

    public HackerComputerPlayer(String name, Board board, Player opponent) {
        super(name, board);
        this.opponent = opponent;
    }

    @Override
    public void placeShips() {
        placeShipsRandomly();
    }

    @Override
    public Coordinates getShootCoords() {
        for (var ship : opponent.getBoard().getShipsOnBoard()) {
            if (!ship.destroyed) {
                for (var pos : ship.getShipParts()) {
                    if (!pos.isHit()) {
                        return new Coordinates(pos.getRow(), pos.getCol());
                    }
                }
            }
        }
        return null;
    }
}
