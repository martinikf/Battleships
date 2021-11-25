package Battleships;

import java.util.Scanner;

public class Game {

    public static int BOARD_HEIGHT;
    public static int BOARD_WIDTH;
    public static int BOATS_COUNT; //loď i -> délka i

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

        players[0] = createPlayer(0);
        players[1] = createPlayer(1);
    }

    private Player createPlayer(int index) {
        //Type
        //Name
        //...
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadej jméno hráče:");
        String name = sc.nextLine();
        System.out.println("Zadej typ hráče: {1-konzole, 2-random, 3-hacker...} enter jméno enter");
        return switch (sc.nextInt()) {
            case 1 -> new ConsolePlayer(name);
            case 2 -> new RandomComputerPlayer(name);
            case 3 -> new HackerComputerPlayer(name, players[1-index]);
            default -> null;
        };
    }

    public void startGame(){
        placeShips();
        printWinner(war());
    }

    private void printWinner(Player war) {
        System.out.println("Winner is: " + war);
    }

    //TODO SCANNER
    private Player war() {
        int turn = 0;

        while(winner == null){
            System.out.println("\n\n\n-------------------------------------------\n\n\n");
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
                    winner = players[turn];
                default:
                case Fault:
            }
        }
        return winner;
    }

    private void placeShips() {
        System.out.println("Player1 boats:");
        players[0].placeBoats();
        System.out.println("Player2 boats:");
        players[1].placeBoats();
    }

    public ShootResult shoot(Player shooter, Player target, int row, int col){
        var result = target.getBoard().shoot(row, col);
        System.out.println(shooter + " vystřelil po: " + target + "Trefa: " + result);
        return result;
    }

    public void saveGame(){}

    public void loadGame(){}
}