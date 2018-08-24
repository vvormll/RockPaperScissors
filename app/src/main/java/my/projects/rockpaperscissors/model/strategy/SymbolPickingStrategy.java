package my.projects.rockpaperscissors.model.strategy;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

public interface SymbolPickingStrategy {

    void update(GameInfo gameInfo);
    Symbol pickSymbol();
    boolean isItTimeToChangeStrategy();

}
