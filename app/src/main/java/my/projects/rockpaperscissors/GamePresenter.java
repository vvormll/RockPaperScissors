package my.projects.rockpaperscissors;

import java.util.ArrayList;
import java.util.List;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.logic.rules.MapRPSLSRuleSet;
import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.logic.symbol.Symbol;
import my.projects.rockpaperscissors.strategy.picker.RandomSymbolStrategyOnlyStrategyPicker;
import my.projects.rockpaperscissors.strategy.picker.StrategyPickerBuilder;

public class GamePresenter {

    private GameController gameController;
    private GameView gameView;

    public GamePresenter() {
        gameController = new GameController();
    }

    public GamePresenter(GameMode gameMode) {
        RuleSet ruleSet;
        switch (gameMode) {
            case ROCK_PAPER_SCISSORS:
                ruleSet = new CircularRPSRuleSet();
                gameController = new GameController(ruleSet, StrategyPickerBuilder.buildDefaultCircularStrategyPicker(ruleSet.getSymbols()));
                break;
            case ROCK_PAPER_SCISSORS_LIZARD_SPOCK:
                ruleSet = new MapRPSLSRuleSet();
                gameController = new GameController(ruleSet, StrategyPickerBuilder.buildRandomSymbolPickingStrategyOnlyStrategyPicker(ruleSet.getSymbols()));
                break;
            default:
                gameController = new GameController();
        }
    }

    public void onAttachView(GameView gameView) {
        if (this.gameView != null) {
            throw new IllegalStateException("There already is an attached View");
        }
        this.gameView = gameView;
    }

    public void onDetachView() {
        if (gameView == null) {
            throw new IllegalStateException("View is not attached");
        }

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

        gameView.initUI(getSymbolStringsListFromController());

        updateGameViewUI(gameController.getGameInfo());
    }

    public void onPickedSymbol(Symbol symbol) {
        if (gameView == null) {
            throw new IllegalStateException("View is not attached");
        }

        gameController.onPlayerInput(symbol);

        updateGameViewUI(gameController.getGameInfo());
    }

    public void onQuitGame() {
        gameController.clearInfo();
    }

    private List<String> getSymbolStringsListFromController() {
        List<String> symbolStrings = new ArrayList<>();
        for (Symbol symbol : gameController.getSymbols()) {
            symbolStrings.add(symbol.name());
        }
        return symbolStrings;
    }

    private void updateGameViewUI(GameInfo gameInfo) {
        int playerScore = gameInfo.getPlayerWins();
        int computerScore = gameInfo.getComputerWins();
        String gameOutcome = (gameInfo.getGameOutcome() == null) ? gameView.getStringWithId(R.string.pick) : gameInfo.getGameOutcome().toString();
        String computerChoice = (gameInfo.getComputerChoice() == null) ? gameView.getStringWithId(R.string.none) : gameInfo.getComputerChoice().name();

        gameView.updateUI(playerScore, computerScore, gameOutcome, computerChoice);
    }

}
