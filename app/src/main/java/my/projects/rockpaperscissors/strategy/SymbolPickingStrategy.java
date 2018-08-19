package my.projects.rockpaperscissors.strategy;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public interface SymbolPickingStrategy {

    void update(GameInfo gameInfo);
    Symbol pickSymbol();
    boolean isItTimeToChangeStrategy();

}
