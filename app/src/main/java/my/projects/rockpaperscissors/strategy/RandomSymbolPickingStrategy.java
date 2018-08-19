package my.projects.rockpaperscissors.strategy;

import java.util.Random;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class RandomSymbolPickingStrategy implements SymbolPickingStrategy {

    private int gameCount;

    public int getGameCountThreshold() {
        return gameCountThreshold;
    }

    private int gameCountThreshold;

    public RandomSymbolPickingStrategy() {
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
        int symbolsSize = Symbol.values().length;

        int choice = rnd.nextInt(symbolsSize);

        return Symbol.values()[choice];
    }

    @Override
    public boolean isItTimeToChangeStrategy() {
        return gameCount > gameCountThreshold;
    }

}
