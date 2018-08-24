package my.projects.rockpaperscissors.model.logic.symbol;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

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
