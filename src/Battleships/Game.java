package Battleships;

import java.util.Scanner;

public class Game {

    private final int gameHeight;
    private final int gameWidth;
    private final int gameShipsCount;

    private final Player[] players = new Player[2];
    private Player winner = null;
    private int turn = 0;

    public Game(int height, int width, int boats) {
        gameHeight = height;
        gameWidth = width;
        gameShipsCount = boats;

        players[0] = createPlayer(0);
        players[1] = createPlayer(1);
    }

    private Player createPlayer(int index) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Zadej jméno hráče "+ (index+1) +".:");
            String name = sc.nextLine();
            System.out.println("Zadej typ hráče: {1-konzole, 2-random, 3-hacker...}:");

            return switch (sc.nextInt()) {
                case 1 -> new ConsolePlayer(name, new Board(gameHeight, gameWidth, gameShipsCount));
                case 2 -> new RandomComputerPlayer(name, new Board(gameHeight, gameWidth, gameShipsCount));
                case 3 -> new HackerComputerPlayer(name, new Board(gameHeight, gameWidth, gameShipsCount), players[1 - index]);
                default -> throw new Exception();
            };
        } catch (Exception ex) {
            System.out.println("Chyba při zadávání hráče. Znovu..");
            return createPlayer(index);
        }
    }

    public void startGame() {
        placeShips();
        printWinner(war());
    }

    private void printWinner(Player winner) {
        System.out.println("Vítěz je: " + winner.getName());
    }

    private Player war() {
        while (winner == null) {
            printGameBoards();

            var shootCoords = players[turn].getShootCoords();
            switch (shoot(players[turn], players[1 - turn], shootCoords.row(), shootCoords.col())) {
                case Miss:
                    turn = 1 - turn;
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

    private void printGameBoards() {
        if (arePlayersLocal()) {
            printBothBoards(false, false);
        }
        else if (players[0].getIsLocalPlayer()) {
            printBothBoards(true, false);
        }
        else {
            printBothBoards(!players[1].getIsLocalPlayer(), true);
        }
    }

    private void printBothBoards(boolean player1, boolean player2) {
        System.out.println(players[0].getName());
        players[0].getBoard().printBoard(player1);

        for (var i = 0; i < gameWidth * 4; i++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.println(players[1].getName());
        players[1].getBoard().printBoard(player2);
    }

    private void placeShips() {
        if (arePlayersLocal()) {
            System.out.println("Pozor! Hrači jsou lokální.");
            System.out.println("Po umístění lodí se už pozice lodí nikdy nezobrazí");
        }
        System.out.println("Lodě hráče: " + players[0].getName());
        players[0].placeShips();
        System.out.println("Lodě hráče: " + players[1].getName());
        players[1].placeShips();
    }

    private boolean arePlayersLocal() {
        for (Player p : players) {
            if (!p.isLocalPlayer) {
                return false;
            }
        }
        return true;
    }

    public ShootResult shoot(Player shooter, Player target, int row, int col) {
        System.out.print(shooter.getName() + " vystřelil po " + target.getName() + " výsledek: ");

        var result = target.getBoard().shoot(row, col);
        System.out.println(result);

        return result;
    }
}