package my.projects.rockpaperscissors.presenter;

import java.util.ArrayList;
import java.util.List;

import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.view.GameView;
import my.projects.rockpaperscissors.R;
import my.projects.rockpaperscissors.model.GameController;
import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.MapRPSLSRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.model.strategy.picker.StrategyPickerBuilder;

public class GamePresenterImpl implements GamePresenter {

    private GameController gameController;
    private GameView gameView;

    public GamePresenterImpl() {
        gameController = new GameController();
    }

    public GamePresenterImpl(GameMode gameMode) {
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

    public GamePresenterImpl(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onAttachView(GameView gameView) {
        if (this.gameView != null) {
            throw new IllegalStateException("There already is an attached View");
        }
        this.gameView = gameView;
    }

    @Override
    public void onDetachView() {
        if (gameView == null) {
            throw new IllegalStateException("View is not attached");
        }

        gameView = null;
    }

    @Override
    public GameInfo getGameInfo() {
        return gameController.getGameInfo();
    }

    @Override
    public void onRestoreState(GameInfo gameInfo) {
        gameController.restoreState(gameInfo);
    }

    @Override
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

    @Override
    public void onPickedSymbol(String symbolString) {
        if (gameView == null) {
            throw new IllegalStateException("View is not attached");
        }

        Symbol symbol = Symbol.valueOf(symbolString);
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

    @Override
    public void onQuitGame() {
        gameController.clearInfo();
    }
}
