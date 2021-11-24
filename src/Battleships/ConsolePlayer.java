package Battleships;

import java.util.Scanner;

public class ConsolePlayer extends Player{

    private Scanner sc;

    public ConsolePlayer(String name){
        super(name);
        sc = new Scanner(System.in);
    }

    //TODO 1) ověřit správnost inputu 2) Zobecnit pro jiné lodě
    //This method has to be rewritten for other than standard boat types
    @Override
    public void placeBoats() {
        int boatsPlaced = 0;

        while(boatsPlaced != 2){
            getBoard().printBoardFull();
            System.out.println("Zadej souřadnice pro loď délky: " + (boatsPlaced + 1) + ", ve tvaru row:column:rotation (0:0:1)");
            String input = sc.nextLine();
            var inputSplit = input.split(":");
            int row = Integer.parseInt(inputSplit[0]);
            int col = Integer.parseInt(inputSplit[1]);
            byte rotation = Byte.parseByte(inputSplit[2]);

            if(board.placeShip(new StraightShip(row, col, rotation, boatsPlaced + 1))){
                boatsPlaced++;
            }
            else{
                System.out.println("Špatně umístěná loď");
            }
        }
    }

    @Override
    public Coordinates getShootCoords() {
        System.out.println("Zadej řádek enter sloupec enter.");
        return new Coordinates(sc.nextInt(), sc.nextInt());
    }

}
