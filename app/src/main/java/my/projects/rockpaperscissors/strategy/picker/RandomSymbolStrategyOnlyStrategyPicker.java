package my.projects.rockpaperscissors.strategy.picker;

import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

public class RandomSymbolStrategyOnlyStrategyPicker implements StrategyPicker {
    @Override
    public SymbolPickingStrategy pickNextStrategy(SymbolPickingStrategy currentStrategy) {
        return new RandomSymbolPickingStrategy();
    }
}
