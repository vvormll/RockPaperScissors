package my.projects.rockpaperscissors.presenter;

import my.projects.rockpaperscissors.view.GameView;
import my.projects.rockpaperscissors.model.info.GameInfo;

public interface GamePresenter {
    void onStartedGame();
    void onQuitGame();
    void onAttachView(GameView gameView);
    void onDetachView();
    void onPickedSymbol(String symbolString);
    void onRestoreState(GameInfo gameInfo);
    GameInfo getGameInfo();
}
