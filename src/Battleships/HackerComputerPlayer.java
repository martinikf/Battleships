package Battleships;

public class HackerComputerPlayer extends ComputerPlayer{

    private final Player opponent;

    public HackerComputerPlayer(String name, Player opponent){
        super(name);
        this.opponent = opponent;
    }

    @Override
    public void placeBoats() {
      placeBoatsRandomly();
    }

    @Override
    public Coordinates getShootCoords() {
        for(var ship : opponent.getBoard().getShipsOnBoard()){
            if(!ship.destroyed){
                for(var pos : ship.getOccupies()){
                    if(!pos.isHit()){
                        return new Coordinates(pos.getRow(), pos.getCol());
                    }
                }
            }
        }
        return null;
    }
}