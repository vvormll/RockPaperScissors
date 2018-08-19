package my.projects.rockpaperscissors.logic.symbol;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

public class SymbolPicker {

    private SymbolPickingStrategy strategy;

    public SymbolPicker(SymbolPickingStrategy strategy) {
        if (strategy == null) {
            throw new NullPointerException("Strategy must not be null");
        }
        this.strategy = strategy;
    }

    public Symbol pickSymbol(GameInfo gameInfo) {
        strategy.update(gameInfo);
        return strategy.pickSymbol();
    }

    public boolean shouldChangeStrategy() {
        return strategy.isItTimeToChangeStrategy();
    }

    public void changeStrategy(SymbolPickingStrategy strategy) {
        this.strategy = strategy;
    }

    public SymbolPickingStrategy getStrategy() {
        return strategy;
    }

}
