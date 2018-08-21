package my.projects.rockpaperscissors.strategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class RandomSymbolPickingStrategy implements SymbolPickingStrategy {

    private List<Symbol> symbolList = new ArrayList<>();

    private int gameCount;

    public int getGameCountThreshold() {
        return gameCountThreshold;
    }

    private int gameCountThreshold;

    public RandomSymbolPickingStrategy(List<Symbol> symbolList) {
        this.symbolList.addAll(symbolList);
        gameCountThreshold = 3;
    }

    public RandomSymbolPickingStrategy(int gameCountThreshold) {
        if (gameCountThreshold <= 0) {
            throw new IllegalArgumentException("Game count threshold must be >= 1");
        }
        this.gameCountThreshold = gameCountThreshold;
    }

    private static Random rnd = new Random();

    @Override
    public void update(GameInfo gameInfo) {
        gameCount++;
    }

    @Override
    public Symbol pickSymbol() {
        int symbolsSize = symbolList.size();

        int choice = rnd.nextInt(symbolsSize);

        return symbolList.get(choice);
    }

    @Override
    public boolean isItTimeToChangeStrategy() {
        return gameCount > gameCountThreshold;
    }

}
