package my.projects.rockpaperscissors;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.game.GameOutcome;
import my.projects.rockpaperscissors.logic.game.PlayerInputProcessor;
import my.projects.rockpaperscissors.logic.game.VictoryConditionChecker;
import my.projects.rockpaperscissors.logic.rules.RuleSetBuilder;
import my.projects.rockpaperscissors.logic.symbol.Symbol;
import my.projects.rockpaperscissors.logic.symbol.SymbolPicker;
import my.projects.rockpaperscissors.strategy.picker.StrategyPicker;
import my.projects.rockpaperscissors.strategy.picker.StrategyPickerBuilder;

public class GameController {
    private GamePresenter gamePresenter;

    private GameInfo gameInfo;
    private SymbolPicker symbolPicker;
    private StrategyPicker strategyPicker;
    private VictoryConditionChecker victoryConditionChecker;

    public GameController() {
        gameInfo = new GameInfo();
        victoryConditionChecker = new VictoryConditionChecker(RuleSetBuilder.buildRockPaperScissorsRuleSet());
        strategyPicker = StrategyPickerBuilder.buildDefaultCircularStrategyPicker();
        symbolPicker = new SymbolPicker(strategyPicker.pickNextStrategy(null));
    }

    public GameController(GameInfo gameInfo, VictoryConditionChecker victoryConditionChecker, StrategyPicker strategyPicker, SymbolPicker symbolPicker) {
        this.gameInfo = gameInfo;
        this.victoryConditionChecker = victoryConditionChecker;
        this.strategyPicker = strategyPicker;
        this.symbolPicker = symbolPicker;
    }

    public void setGamePresenter(GamePresenter gamePresenter) {
        this.gamePresenter = gamePresenter;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void restoreState(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void onGameStarted() {
        if (gamePresenter == null) {
            throw new IllegalStateException("GamePresenter must be set first");
        }
        gamePresenter.updateUI(gameInfo);
    }

    public void onQuitInput() {
        if (gamePresenter == null) {
            throw new IllegalStateException("GamePresenter must be set first");
        }

        gameInfo.clearScore();
        gamePresenter = null;
    }

    public void onPlayerInput(Symbol playerChoice) {
        if (gamePresenter == null) {
            throw new IllegalStateException("GamePresenter must be set first");
        }

        Symbol computerChoice = symbolPicker.pickSymbol(gameInfo);
        GameOutcome gameOutcome = victoryConditionChecker.checkOutcome(playerChoice, computerChoice);
        gameInfo.updateInfo(playerChoice, computerChoice, gameOutcome);

        if (symbolPicker.shouldChangeStrategy()) {
            symbolPicker.changeStrategy(strategyPicker.pickNextStrategy(symbolPicker.getStrategy()));
        }

        gamePresenter.updateUI(gameInfo);
    }

}
