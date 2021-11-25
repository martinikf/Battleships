package Battleships;

public interface IGameSaver {

    boolean saveGame();

    boolean loadGame(String savename);
}
