package Battleships;

public interface IGameSaver {

    boolean saveGame(String saveName, Game game);

    boolean loadGame(String saveName);
}
