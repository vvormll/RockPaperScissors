package my.projects.rockpaperscissors;

import java.util.List;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.game.GameOutcome;
import my.projects.rockpaperscissors.logic.game.PlayerInputProcessor;
import my.projects.rockpaperscissors.logic.game.VictoryConditionChecker;
import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.logic.rules.RuleSetBuilder;
import my.projects.rockpaperscissors.logic.symbol.Symbol;
import my.projects.rockpaperscissors.logic.symbol.SymbolPicker;
import my.projects.rockpaperscissors.strategy.picker.StrategyPicker;
import my.projects.rockpaperscissors.strategy.picker.StrategyPickerBuilder;

public class GameController {
    private GameInfo gameInfo;
    private SymbolPicker symbolPicker;
    private StrategyPicker strategyPicker;
    private VictoryConditionChecker victoryConditionChecker;
    private RuleSet ruleSet;

    public GameController() {
        gameInfo = new GameInfo();
        ruleSet = RuleSetBuilder.buildRockPaperScissorsRuleSet();
        victoryConditionChecker = new VictoryConditionChecker(ruleSet);
        strategyPicker = StrategyPickerBuilder.buildDefaultCircularStrategyPicker();
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
