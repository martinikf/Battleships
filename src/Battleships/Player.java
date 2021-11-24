package Battleships;

public abstract class Player {

    protected Board board;
    protected String name;

    public Player(String name){
        board = new Board();
        this.name = name;
    }

    public abstract void placeBoats();

    public Board getBoard(){return board;}
    public String getName(){return name;}
}
