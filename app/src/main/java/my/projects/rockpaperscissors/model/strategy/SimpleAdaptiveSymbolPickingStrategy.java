package my.projects.rockpaperscissors.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.rules.RockPaperScissorsRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;


//symbol picking strategy which is an implementation of
//https://www.youtube.com/watch?v=rudzYPHuewc
//works only for rock-paper-scissors ruleset
public class SimpleAdaptiveSymbolPickingStrategy implements SymbolPickingStrategy {

    private List<Symbol> symbolList;

    private int consecutiveLosses;
    private int consecutiveLossesThreshold;

    private Random rnd = new Random();
    private Symbol nextSymbol;

    public SimpleAdaptiveSymbolPickingStrategy(RockPaperScissorsRuleSet ruleSet)  {
        this.symbolList = ruleSet.getSymbols();
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
        int symbolsSize = symbolList.size();

        int choice = rnd.nextInt(symbolsSize);

        return symbolList.get(choice);
    }

    public int getConsecutiveLossesThreshold() {
        return consecutiveLossesThreshold;
    }
}
