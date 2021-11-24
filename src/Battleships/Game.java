package Battleships;

public class Game {

    public static int BOARD_HEIGHT = 8;
    public static int BOARD_WIDTH = 8;

    private final Player player1;
    private final Player player2;
    private Player winner = null;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Game(int height, int width){
        BOARD_HEIGHT = height;
        BOARD_WIDTH = width;

        player1 = createPlayer();
        player2 = createPlayer();
    }

    private Player createPlayer() {
        //Type
        //Name
        //...
        return new ConsolePlayer("Honza");
    }

    public void startGame(){
        placeShips(player1);
        placeShips(player2);
        winner = war();
    }

    private Player war() {
        return null;
    }

    private void placeShips(Player player) {}

    public void saveGame(){}

    public void loadGame(){}

}
