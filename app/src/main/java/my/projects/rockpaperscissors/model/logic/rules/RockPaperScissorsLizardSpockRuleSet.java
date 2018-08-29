package my.projects.rockpaperscissors.model.logic.rules;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

public abstract class RockPaperScissorsLizardSpockRuleSet implements RuleSet {

    //effectively unmodifiable
    private final List<Symbol> symbolSet = new LinkedList<>(Arrays.asList(Symbol.ROCK, Symbol.PAPER, Symbol.SCISSORS, Symbol.LIZARD, Symbol.SPOCK));

    @Override
    public List<Symbol> getSymbols() {
        return symbolSet;
    }
}
