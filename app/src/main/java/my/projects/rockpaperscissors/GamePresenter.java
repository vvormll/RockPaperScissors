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

    public GamePresenter(GameController gameController) {
        this.gameController = gameController;
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

        gameView.initUIButtons(getSymbolStringsListFromController());

        GameInfo gameInfo = getGameInfo();
        gameView.updateUI(gameInfo.getPlayerWins(), gameInfo.getComputerWins(), getGameOutcomeString(gameInfo), getComputerChoiceString(gameInfo));
    }

    private List<String> getSymbolStringsListFromController() {
        List<String> symbolStrings = new ArrayList<>();
        for (Symbol symbol : gameController.getSymbols()) {
            symbolStrings.add(symbol.name());
        }
        return symbolStrings;
    }

    public void onPickedSymbol(Symbol symbol) {
        if (gameView == null) {
            throw new IllegalStateException("View is not attached");
        }

        gameController.onPlayerInput(symbol);

        GameInfo gameInfo = getGameInfo();
        gameView.updateUI(gameInfo.getPlayerWins(), gameInfo.getComputerWins(), getGameOutcomeString(gameInfo), getComputerChoiceString(gameInfo));
    }

    private String getGameOutcomeString(GameInfo gameInfo) {
        return (gameInfo.getGameOutcome() == null) ? gameView.getStringWithId(R.string.pick) : gameInfo.getGameOutcome().toString();
    }

    private String getComputerChoiceString(GameInfo gameInfo) {
        return (gameInfo.getComputerChoice() == null) ? gameView.getStringWithId(R.string.none) : gameInfo.getComputerChoice().name();
    }

    public void onQuitGame() {
        gameController.clearInfo();
    }
}
