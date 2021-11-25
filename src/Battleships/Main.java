package Battleships;

public class Main {

    public static void main(String[] args) throws Exception {


        Game g = new Game(4, 4, 2);
        /*System.out.println(g.getPlayer1().name);
        g.getPlayer1().getBoard().placeShip(new StraightShip(0, 0, (byte)0, 3));
        g.getPlayer1().getBoard().printBoard();
        try{
            g.getPlayer1().getBoard().shoot(0, 0);
            g.getPlayer1().getBoard().shoot(0,1);
            g.getPlayer1().getBoard().shoot(0, 2);
            g.getPlayer1().getBoard().shoot(0, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.getPlayer1().getBoard().printBoard();*/
        g.startGame();

    }
}