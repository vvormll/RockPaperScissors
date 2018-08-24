package my.projects.rockpaperscissors.model.strategy.picker;

import java.util.List;

import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.model.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

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
