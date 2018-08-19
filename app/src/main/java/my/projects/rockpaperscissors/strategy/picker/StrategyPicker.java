package my.projects.rockpaperscissors.strategy.picker;

import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

public interface StrategyPicker {
    SymbolPickingStrategy pickNextStrategy(SymbolPickingStrategy currentStrategy);
}
