package Battleships;

public abstract class ComputerPlayer extends Player{

    public ComputerPlayer(String name) {
        super(name);
        isLocalPlayer = false;
    }
}
