package my.projects.rockpaperscissors;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class GamePresenter {

    private GameController gameController;
    private GameView gameView;

    public GamePresenter(GameView gameView) {
        this.gameView = gameView;
    }

    public void setGameController(GameController gameController) {
        gameController.setGamePresenter(this);
        this.gameController = gameController;
    }

    public GameInfo getGameInfo() {
        return gameController.getGameInfo();
    }

    public void onRestoreState(GameInfo gameInfo) {
        gameController.restoreState(gameInfo);
    }

    public void onStartedGame() {
        gameController.onGameStarted();
    }

    public void onPickedSymbol(Symbol symbol) {
        gameController.onPlayerInput(symbol);
    }

    public void onQuitGame() {
        gameController.onQuitInput();
        gameController = null;
    }

    public void updateUI(GameInfo gameInfo) {
        int playerScore = gameInfo.getPlayerWins();
        int computerScore = gameInfo.getComputerWins();
        String gameOutcome = (gameInfo.getGameOutcome() == null) ? "Pick" : gameInfo.getGameOutcome().toString();
        String computerChoice = (gameInfo.getComputerChoice() == null) ? "None" : gameInfo.getComputerChoice().name();

        gameView.updateUI(playerScore, computerScore, gameOutcome, computerChoice);
    }

}
