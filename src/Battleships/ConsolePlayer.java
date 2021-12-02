package Battleships;

import java.util.Locale;
import java.util.Scanner;

public class ConsolePlayer extends HumanLocalPlayer{

    private final Scanner sc;

    public ConsolePlayer(String name, Board board){
        super(name, board);
        sc = new Scanner(System.in);
    }

    //TODO 1) ověřit správnost inputu 2) Zobecnit pro jiné lodě
    //This method has to be rewritten for other than standard (straight) boat types
    @Override
    public void placeShips() {
        System.out.println("Pokud chcete lodě umístit automaticky napište: 'Y'.");

        if (sc.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
            placeBoatsRandomly();
        } else {
            int boatsPlaced = 0;

            while (boatsPlaced < getBoard().getShipsCount()) {
                getBoard().printBoardPlayersPerspective();
                System.out.println("Zadej souřadnice pro loď délky: " + (boatsPlaced + 1) + ", ve tvaru row:column:rotation (0:0:1)");

                try {
                    String input = sc.nextLine();

                    var inputSplit = input.split(":");
                    int row = Integer.parseInt(inputSplit[0]);
                    int col = Integer.parseInt(inputSplit[1]);
                    byte rotation = Byte.parseByte(inputSplit[2]);

                    if (board.placeShip(new StraightShip(row, col, rotation, boatsPlaced + 1))) {
                        boatsPlaced++;
                    } else {
                        System.out.println("Špatně umístěná loď");
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
        }
    }

    @Override
    public Coordinates getShootCoords() {
        System.out.println("Zadej řádek enter sloupec enter.");
        try {
            return new Coordinates(sc.nextInt(), sc.nextInt());
        }
        catch(Exception ex){
            sc.nextLine();
            return getShootCoords();
        }
    }
}