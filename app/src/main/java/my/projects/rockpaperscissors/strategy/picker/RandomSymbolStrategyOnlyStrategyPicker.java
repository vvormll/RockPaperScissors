package my.projects.rockpaperscissors.strategy.picker;

import java.util.List;
import java.util.Set;

import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.logic.symbol.Symbol;
import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

public class RandomSymbolStrategyOnlyStrategyPicker implements StrategyPicker {

    private RandomSymbolPickingStrategy strategy;

    public RandomSymbolStrategyOnlyStrategyPicker(List<Symbol> symbolSet) {
        strategy = new RandomSymbolPickingStrategy(symbolSet);
    }

    @Override
    public SymbolPickingStrategy pickNextStrategy(SymbolPickingStrategy currentStrategy) {
        return strategy;
    }
}
