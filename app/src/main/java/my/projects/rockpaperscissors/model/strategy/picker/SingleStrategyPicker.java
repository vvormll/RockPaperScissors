package my.projects.rockpaperscissors.model.strategy.picker;

import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

public class SingleStrategyPicker implements StrategyPicker {

    private SymbolPickingStrategy strategy;

    public SingleStrategyPicker(SymbolPickingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public SymbolPickingStrategy pickNextStrategy(SymbolPickingStrategy currentStrategy) {
        return strategy;
    }
}
