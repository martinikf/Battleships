package Battleships;

public abstract class HumanLocalPlayer extends Player {

    public HumanLocalPlayer(String name, Board board) {
        super(name, board);
        isLocalPlayer = true;
    }
}
