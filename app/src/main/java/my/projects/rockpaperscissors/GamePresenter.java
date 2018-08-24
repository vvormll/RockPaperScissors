package my.projects.rockpaperscissors;

import my.projects.rockpaperscissors.info.GameInfo;

public interface GamePresenter {
    void onStartedGame();
    void onQuitGame();
    void onAttachView(GameView gameView);
    void onDetachView();
    void onPickedSymbol(String symbolString);
    void onRestoreState(GameInfo gameInfo);
    GameInfo getGameInfo();
}
