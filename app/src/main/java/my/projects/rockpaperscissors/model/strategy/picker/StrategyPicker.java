package my.projects.rockpaperscissors.model.strategy.picker;

import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

public interface StrategyPicker {
    SymbolPickingStrategy pickNextStrategy(SymbolPickingStrategy currentStrategy);
}
