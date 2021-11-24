package Battleships;

public class Game {

    public static int BOARD_HEIGHT = 8;
    public static int BOARD_WIDTH = 8;
    public static int BOATS_COUNT = 1; //loď i -> délka i

    private final Player[] players = new Player[2];
    private Player winner = null;

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public Game(int height, int width, int boats){
        BOARD_HEIGHT = height;
        BOARD_WIDTH = width;
        BOATS_COUNT = boats;

        players[0] = createPlayer();
        players[1] = createPlayer();
    }

    private Player createPlayer() {
        //Type
        //Name
        //...
        return new ConsolePlayer("Honza");
    }

    public void startGame(){
        placeShips();
        war();
    }

    //TODO SCANNER
    private Player war() {
        int turn = 0;

        while(winner == null){
            System.out.println("Player1 board:");
            players[0].getBoard().printBoardPlayersPerspective();
            System.out.println("Player2 board:");
            players[1].getBoard().printBoardPlayersPerspective();
            System.out.println("---------------------------------------");

            System.out.println("Turn: " + turn);

            var shootCoords = players[turn].getShootCoords();

            switch(shoot(players[turn], players[1-turn], shootCoords.row(), shootCoords.col())){
                case Hit:
                    continue;
                case Miss:
                    turn = 1-turn;
                    break;
                case Win:
                    System.out.println("Player - " + players[turn] + " has won!");
                    return players[turn];
                default:
                case Fault:
                    return null;
            }
        }
        return null;
    }

    private void placeShips() {
        System.out.println("Player1 boats:");
        players[0].placeBoats();
        System.out.println("Player2 boats:");
        players[1].placeBoats();
    }

    public ShootResult shoot(Player shooter, Player target, int row, int col){
        System.out.println(shooter + " vystřelil po: " + target + "Trefa: " );
        return target.getBoard().shoot(row, col);
    }

    public void saveGame(){}

    public void loadGame(){}
}