package Battleships;

public class Game {

    public static int BOARD_HEIGHT;
    public static int BOARD_WIDTH;

    private final Player player1;
    private final Player player2;
    private Player winner = null;

    public Game(int height, int width){
        BOARD_HEIGHT = height;
        BOARD_WIDTH = width;

        player1 = createPlayer();
        player2 = createPlayer();
    }

    private Player createPlayer() {
        //Human vybere typ hráče z konzole
        //Zadá jméno
        //Vytvoří se instance
        return null;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void startGame(){
       placeShips();
       winner = war();
    }

    private Player war() {
        return null;
    }

    private void placeShips() {}

    public void saveGame(){}

    public void loadGame(){}

}
