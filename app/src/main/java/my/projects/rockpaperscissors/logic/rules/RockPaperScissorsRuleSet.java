package my.projects.rockpaperscissors.logic.rules;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

public abstract class RockPaperScissorsRuleSet implements RuleSet {

    private List<Symbol> symbolSet = new LinkedList<>(Arrays.asList(Symbol.ROCK, Symbol.PAPER, Symbol.SCISSORS));

    @Override
    public List<Symbol> getSymbols() {
        return new LinkedList<>(symbolSet);
    }
}
