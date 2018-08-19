package my.projects.rockpaperscissors.strategy;

import java.util.Random;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class SimpleAdaptiveSymbolPickingStrategy implements SymbolPickingStrategy {

    private int consecutiveLosses;


    private int consecutiveLossesThreshold;

    private Random rnd = new Random();
    private Symbol nextSymbol;

    public SimpleAdaptiveSymbolPickingStrategy() {
        consecutiveLossesThreshold = 2;
    }

    public SimpleAdaptiveSymbolPickingStrategy(int consecutiveLossesThreshold) {
        this.consecutiveLossesThreshold = consecutiveLossesThreshold;
    }

    @Override
    public void update(GameInfo gameInfo) {
        if (gameInfo.isFirstGame()) {
            nextSymbol = pickRandomSymbol();
        }

        switch (gameInfo.getGameOutcome()) {
            case PLAYER_WIN: // player won last time
                consecutiveLosses++;
                switch (gameInfo.getPlayerChoice()) {
                    case ROCK:
                        nextSymbol = Symbol.PAPER;
                        return;
                    case SCISSORS:
                        nextSymbol = Symbol.ROCK;
                        return;
                    case PAPER:
                        nextSymbol = Symbol.SCISSORS;
                        return;
                }
            case PLAYER_LOSS: // player lost last time
                consecutiveLosses = 0;
                nextSymbol =  gameInfo.getPlayerChoice();
                break;
            case DRAW:
                nextSymbol = pickRandomSymbol();
                break;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public Symbol pickSymbol() {
        if (nextSymbol == null) {
            throw new IllegalStateException("Update must be called before pickSymbol");
        }
        Symbol toReturn = nextSymbol;
        nextSymbol = null;
        return toReturn;
    }

    @Override
    public boolean isItTimeToChangeStrategy() {
        return consecutiveLosses > consecutiveLossesThreshold;
    }

    private Symbol pickRandomSymbol() {
        int symbolsSize = Symbol.values().length;

        int choice = rnd.nextInt(symbolsSize);

        return Symbol.values()[choice];
    }

    public int getConsecutiveLossesThreshold() {
        return consecutiveLossesThreshold;
    }
}
