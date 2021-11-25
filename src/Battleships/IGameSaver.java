package Battleships;

public interface IGameSaver {

    boolean saveGame(String saveName);

    boolean loadGame(String saveName);
}
