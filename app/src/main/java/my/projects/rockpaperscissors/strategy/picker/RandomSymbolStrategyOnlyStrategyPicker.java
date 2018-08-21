package my.projects.rockpaperscissors.strategy.picker;

import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

public class RandomSymbolStrategyOnlyStrategyPicker implements StrategyPicker {

    private RuleSet ruleSet;

    public RandomSymbolStrategyOnlyStrategyPicker(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    public SymbolPickingStrategy pickNextStrategy(SymbolPickingStrategy currentStrategy) {
        return new RandomSymbolPickingStrategy(ruleSet.getSymbols());
    }
}
