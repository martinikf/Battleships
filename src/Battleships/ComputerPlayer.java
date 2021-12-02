package Battleships;

public abstract class ComputerPlayer extends Player{

    public ComputerPlayer(String name, Board board) {
        super(name, board);
        isLocalPlayer = false;
    }
}
