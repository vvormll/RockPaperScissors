package my.projects.rockpaperscissors.model;

import java.util.List;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.game.GameOutcome;
import my.projects.rockpaperscissors.model.logic.game.VictoryConditionChecker;
import my.projects.rockpaperscissors.model.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RockPaperScissorsRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.model.logic.symbol.SymbolPicker;
import my.projects.rockpaperscissors.model.strategy.picker.StrategyPicker;
import my.projects.rockpaperscissors.model.strategy.picker.StrategyPickerFactory;

public class GameController {
    private GameInfo gameInfo;
    private SymbolPicker symbolPicker;
    private StrategyPicker strategyPicker;
    private VictoryConditionChecker victoryConditionChecker;
    private RuleSet ruleSet;

    public GameController(RuleSet ruleSet, StrategyPicker strategyPicker) {
        this.ruleSet = ruleSet;
        this.strategyPicker = strategyPicker;

        gameInfo = new GameInfo();
        victoryConditionChecker = new VictoryConditionChecker(this.ruleSet);
        symbolPicker = new SymbolPicker(strategyPicker.pickNextStrategy());
    }

    private GameController() {}

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void restoreState(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void clearInfo() {
        gameInfo.clearScore();
    }

    public void onPlayerInput(Symbol playerChoice) {
        Symbol computerChoice = symbolPicker.pickSymbol(gameInfo);
        GameOutcome gameOutcome = victoryConditionChecker.checkOutcome(playerChoice, computerChoice);
        gameInfo.updateInfo(playerChoice, computerChoice, gameOutcome);

        if (symbolPicker.shouldChangeStrategy()) {
            symbolPicker.changeStrategy(strategyPicker.pickNextStrategy());
        }
    }

    public List<Symbol> getSymbols() {
        return ruleSet.getSymbols();
    }

    public static GameControllerBuilder builder() {
        return new GameControllerBuilder();
    }

    public static class GameControllerBuilder {
        private GameController gameController = new GameController();

        public GameControllerBuilder gameInfo(GameInfo gameInfo) {
            gameController.gameInfo = gameInfo;
            return this;
        }

        public GameControllerBuilder symbolPicker(SymbolPicker symbolPicker) {
            gameController.symbolPicker = symbolPicker;
            return this;
        }

        public GameControllerBuilder strategyPicker(StrategyPicker strategyPicker) {
            gameController.strategyPicker = strategyPicker;
            return this;
        }

        public GameControllerBuilder victoryConditionChecker(VictoryConditionChecker victoryConditionChecker) {
            gameController.victoryConditionChecker = victoryConditionChecker;
            return this;
        }

        public GameControllerBuilder ruleSet(RuleSet ruleSet) {
            gameController.ruleSet = ruleSet;
            return this;
        }

        public GameController build() {
            if (anyOfFieldsIsNull()) {
                throw new IllegalStateException("All of GameController fields must be initialized before building");
            }
            return gameController;
        }

        private boolean anyOfFieldsIsNull() {
            return gameController.gameInfo == null ||
                    gameController.symbolPicker == null ||
                    gameController.ruleSet == null ||
                    gameController.strategyPicker == null ||
                    gameController.victoryConditionChecker == null;
        }
    }

}
