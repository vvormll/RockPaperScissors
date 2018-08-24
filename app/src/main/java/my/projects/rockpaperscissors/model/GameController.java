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

    public GameController() {
        gameInfo = new GameInfo();
        ruleSet = new CircularRPSRuleSet();
        victoryConditionChecker = new VictoryConditionChecker(ruleSet);
        strategyPicker = StrategyPickerBuilder.buildDefaultCircularStrategyPicker(ruleSet.getSymbols());
        symbolPicker = new SymbolPicker(strategyPicker.pickNextStrategy(null));
    }

    public GameController(RuleSet ruleSet, StrategyPicker strategyPicker) {
        gameInfo = new GameInfo();
        this.ruleSet = ruleSet;
        victoryConditionChecker = new VictoryConditionChecker(ruleSet);
        this.strategyPicker = strategyPicker;
        symbolPicker = new SymbolPicker(strategyPicker.pickNextStrategy(null));
    }

    public GameController(GameInfo gameInfo, VictoryConditionChecker victoryConditionChecker, StrategyPicker strategyPicker, SymbolPicker symbolPicker) {
        this.gameInfo = gameInfo;
        this.victoryConditionChecker = victoryConditionChecker;
        this.strategyPicker = strategyPicker;
        this.symbolPicker = symbolPicker;
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
            symbolPicker.changeStrategy(strategyPicker.pickNextStrategy(symbolPicker.getStrategy()));
        }
    }

    public List<Symbol> getSymbols() {
        return ruleSet.getSymbols();
    }

}