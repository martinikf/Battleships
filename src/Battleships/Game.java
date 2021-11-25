package Battleships;

import java.util.Scanner;

public class Game {

    public static int BOARD_HEIGHT;
    public static int BOARD_WIDTH;
    public static int BOATS_COUNT; //loď i -> délka i
    public static int PLAYER_COUNT; //TODO make game playable for more than 2 players

    private final Player[] players = new Player[2];
    private Player winner = null;

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

    private void printWinner(Player winner) {
        System.out.println("Winner is: " + winner.getName());
    }

    //TODO rewrite mess!
    private Player war() {
        int turn = 0;

        while(winner == null){
            System.out.println("\n-------------------------------------------\n");
            System.out.println("Turn: " + players[turn].getName());
            if(arePlayersLocal()){
                System.out.println("Player1 board:");
                players[0].getBoard().printBoardOpponentsPerspective();
                System.out.println("Player2 board:");
                players[1].getBoard().printBoardOpponentsPerspective();
            }
            else if(turn == 0){
                System.out.println("Player1 board:");
                players[0].getBoard().printBoardPlayersPerspective();
                System.out.println("Player2 board:");
                players[1].getBoard().printBoardOpponentsPerspective();
            }
            else{
                System.out.println("Player1 board:");
                players[0].getBoard().printBoardOpponentsPerspective();
                System.out.println("Player2 board:");
                players[1].getBoard().printBoardPlayersPerspective();
            }

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
        if(arePlayersLocal()){
            System.out.println("Pozor! Hrači jsou lokální.");
            System.out.println("Po umístění lodí se už pozice lodí nikdy nezobrazí");
        }
        System.out.println("Player1 boats:");
        players[0].placeBoats();
        System.out.println("Player2 boats:");
        players[1].placeBoats();
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

    public void saveGame(){
        IGameSaver gs = new TXTGameSaver();
        gs.saveGame();
    }

    public void loadGame(String saveName){
        IGameSaver gs = new TXTGameSaver();
        gs.loadGame(saveName);
    }
}