package Battleships;

public abstract class HumanLocalPlayer extends Player{

    public HumanLocalPlayer(String name) {
        super(name);
        isLocalPlayer = true;
    }
}
