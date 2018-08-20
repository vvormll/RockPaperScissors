package my.projects.rockpaperscissors;

import java.util.ArrayList;
import java.util.List;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class GamePresenter {

    private GameController gameController;
    private GameView gameView;

    public GamePresenter() {
        gameController = new GameController();
    }

    public void onAttachView(GameView gameView) {
        if (this.gameView != null) {
            throw new IllegalStateException("There already is an attached View");
        }
        this.gameView = gameView;
    }

    public void onDetachView() {
        gameView = null;
    }

    public GameInfo getGameInfo() {
        return gameController.getGameInfo();
    }

    public void onRestoreState(GameInfo gameInfo) {
        gameController.restoreState(gameInfo);
    }

    public void onStartedGame() {
        if (gameView == null) {
            throw new IllegalStateException("View is not attached");
        }

        initUIWithSymbols();
        updateUI(gameController.getGameInfo());
    }

    public void onPickedSymbol(Symbol symbol) {
        if (gameView == null) {
            throw new IllegalStateException("View is not attached");
        }

        gameController.onPlayerInput(symbol);
        updateUI(gameController.getGameInfo());
    }

    public void onQuitGame() {
        gameController.clearInfo();
    }

    private void initUIWithSymbols() {
        List<Symbol> symbols = gameController.getSymbols();
        List<String> symbolStrings = new ArrayList<>();
        for (Symbol symbol : symbols) {
            symbolStrings.add(symbol.name());
        }
        gameView.initUI(symbolStrings);
    }

    private void updateUI(GameInfo gameInfo) {
        int playerScore = gameInfo.getPlayerWins();
        int computerScore = gameInfo.getComputerWins();
        String gameOutcome = (gameInfo.getGameOutcome() == null) ? "Pick" : gameInfo.getGameOutcome().toString();
        String computerChoice = (gameInfo.getComputerChoice() == null) ? "None" : gameInfo.getComputerChoice().name();

        gameView.updateUI(playerScore, computerScore, gameOutcome, computerChoice);
    }

}
