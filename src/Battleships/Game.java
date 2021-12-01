package Battleships;

import java.util.Scanner;

public class Game {

    public static int BOARD_HEIGHT;
    public static int BOARD_WIDTH;
    public static int BOATS_COUNT;

    private final Player[] players = new Player[2];
    private Player winner = null;
    private int turn = 0;

    public Player[] getPlayers(){
        return players;
    }
    public Player getWinner(){return winner;}
    public int getTurn(){return turn;}

    public Game(int height, int width, int boats){
        BOARD_HEIGHT = height;
        BOARD_WIDTH = width;
        BOATS_COUNT = boats;

        players[0] = createPlayer(0);
        players[1] = createPlayer(1);
    }

    private Player createPlayer(int index) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadej jméno hráče:");
        String name = sc.nextLine();
        System.out.println("Zadej typ hráče: {1-konzole, 2-random, 3-hacker...} enter jméno enter");
        try {
            return switch (sc.nextInt()) {
                case 1 -> new ConsolePlayer(name);
                case 2 -> new RandomComputerPlayer(name);
                case 3 -> new HackerComputerPlayer(name, players[1 - index]);
                default -> null;
            };
        }
        catch (Exception ex){
            System.out.println("Chyba při zadávání hráče. Znovu..");
            createPlayer(index);
        }
        return null;
    }

    public void startGame(){
        placeShips();
        printWinner(war());
    }

    private void printWinner(Player winner) {
        System.out.println("Winner is: " + winner.getName());
    }

    private Player war() {
        while(winner == null){
            printGameBoards(turn);

            var shootCoords = players[turn].getShootCoords();
            switch(shoot(players[turn], players[1-turn], shootCoords.row(), shootCoords.col())){
                case Miss:
                    turn = 1-turn;
                    break;
                case Win:
                    winner = players[turn];
                    break;
                default:
                case Fault, Hit:
            }
        }
        return winner;
    }

    private void printGameBoards(int turn) {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Turn: " + players[turn].getName());
        if(arePlayersLocal()){
            players[0].getBoard().printBoardOpponentsPerspective();
            System.out.println("---------------");
            players[1].getBoard().printBoardOpponentsPerspective();
        }
        else if(players[0].getIsLocalPlayer()){
            players[0].getBoard().printBoardPlayersPerspective();
            System.out.println("---------------");
            players[1].getBoard().printBoardOpponentsPerspective();
        }
        else if(players[1].getIsLocalPlayer()){
            players[0].getBoard().printBoardOpponentsPerspective();
            System.out.println("---------------");
            players[1].getBoard().printBoardPlayersPerspective();
        }
        else{
            players[0].getBoard().printBoardPlayersPerspective();
            System.out.println("---------------");
            players[1].getBoard().printBoardPlayersPerspective();
        }
    }

    private void placeShips() {
        if(arePlayersLocal()){
            System.out.println("Pozor! Hrači jsou lokální.");
            System.out.println("Po umístění lodí se už pozice lodí nikdy nezobrazí");
        }
        System.out.println("Player1 boats:");
        players[0].placeShips();
        System.out.println("Player2 boats:");
        players[1].placeShips();
    }

    private boolean arePlayersLocal(){
        for(Player p : players){
            if(!p.isLocalPlayer){
                return false;
            }
        }
        return true;
    }

    public ShootResult shoot(Player shooter, Player target, int row, int col){
        System.out.print(shooter.getName() + " vystřelil po " + target.getName() + " výsledek: ");

        var result = target.getBoard().shoot(row, col);
        System.out.println(result);

        return result;
    }
}