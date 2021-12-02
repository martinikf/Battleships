package Battleships;

import java.util.Locale;
import java.util.Scanner;

public class ConsolePlayer extends HumanLocalPlayer{

    private final Scanner sc;

    public ConsolePlayer(String name, Board board){
        super(name, board);
        sc = new Scanner(System.in);
    }

    @Override
    public void placeShips() {
        System.out.println("Pokud chcete lodě umístit automaticky napište: 'Y'.");

        if (sc.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
            placeBoatsRandomly();
        }
        else {
            int boatsPlaced = 0;

            while (boatsPlaced < getBoard().getShipsCount()) {
                getBoard().printBoard(true);
                System.out.println("Zadej souřadnice pro loď délky: " + (boatsPlaced + 1) + ", ve tvaru row:column:rotation (0:0:1) / (rotatace = 0 -> vodorovně rotace = 1 -> směr dolů)");

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
        System.out.println("Střela hráče: " + name);
        System.out.println("Zadej souřadnice střely row:col");
        try {
            var input = sc.nextLine();
            var splitInput = input.split(":");
            return new Coordinates(Integer.parseInt(splitInput[0]), Integer.parseInt(splitInput[1]));
        }
        catch(Exception ex){
            sc.nextLine();
            return getShootCoords();
        }
    }
}