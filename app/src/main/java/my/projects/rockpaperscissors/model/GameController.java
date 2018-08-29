package my.projects.rockpaperscissors.model;

import java.util.List;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.game.GameOutcome;
import my.projects.rockpaperscissors.model.logic.game.VictoryConditionChecker;
import my.projects.rockpaperscissors.model.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.model.logic.symbol.SymbolPicker;
import my.projects.rockpaperscissors.model.strategy.picker.StrategyPicker;
import my.projects.rockpaperscissors.model.strategy.picker.StrategyPickerBuilder;

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

    private GameController(GameInfo gameInfo, VictoryConditionChecker victoryConditionChecker, StrategyPicker strategyPicker, SymbolPicker symbolPicker, RuleSet ruleSet) {
        this.gameInfo = gameInfo;
        this.victoryConditionChecker = victoryConditionChecker;
        this.strategyPicker = strategyPicker;
        this.symbolPicker = symbolPicker;
        this.ruleSet = ruleSet;
    }

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
        private GameInfo gameInfo;
        private SymbolPicker symbolPicker;
        private StrategyPicker strategyPicker;
        private VictoryConditionChecker victoryConditionChecker;
        private RuleSet ruleSet;

        public GameControllerBuilder gameInfo(GameInfo gameInfo) {
            this.gameInfo = gameInfo;
            return this;
        }

        public GameControllerBuilder symbolPicker(SymbolPicker symbolPicker) {
            this.symbolPicker = symbolPicker;
            return this;
        }

        public GameControllerBuilder strategyPicker(StrategyPicker strategyPicker) {
            this.strategyPicker = strategyPicker;
            return this;
        }

        public GameControllerBuilder victoryConditionChecker(VictoryConditionChecker victoryConditionChecker) {
            this.victoryConditionChecker = victoryConditionChecker;
            return this;
        }

        public GameControllerBuilder ruleSet(RuleSet ruleSet) {
            this.ruleSet = ruleSet;
            return this;
        }

        public GameController build() {
            if (gameInfo == null) {
                gameInfo = new GameInfo();
            }
            if (ruleSet == null) {
                ruleSet = new CircularRPSRuleSet();
            }
            if (strategyPicker == null) {
                strategyPicker = StrategyPickerBuilder.buildDefaultCircularStrategyPicker(ruleSet.getSymbols());
            }
            if (symbolPicker == null) {
                symbolPicker = new SymbolPicker(strategyPicker.pickNextStrategy());
            }
            if (victoryConditionChecker == null) {
                victoryConditionChecker = new VictoryConditionChecker(ruleSet);
            }
            return new GameController(gameInfo, victoryConditionChecker, strategyPicker, symbolPicker, ruleSet);
        }
    }

}
