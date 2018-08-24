package my.projects.rockpaperscissors.model.logic.rules;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

public class MapRPSLSRuleSet extends RockPaperScissorsLizardSpockRuleSet {

    private Map<Symbol, List<Symbol>> symbolRelations = new HashMap<>();

    {
        symbolRelations.put(Symbol.ROCK, Arrays.asList(Symbol.LIZARD, Symbol.SCISSORS));
        symbolRelations.put(Symbol.PAPER, Arrays.asList(Symbol.SPOCK, Symbol.ROCK));
        symbolRelations.put(Symbol.SCISSORS, Arrays.asList(Symbol.PAPER, Symbol.LIZARD));
        symbolRelations.put(Symbol.LIZARD, Arrays.asList(Symbol.SPOCK, Symbol.PAPER));
        symbolRelations.put(Symbol.SPOCK, Arrays.asList(Symbol.SCISSORS, Symbol.ROCK));
    }

    @Override
    public SymbolComparisonResult compareSymbols(Symbol symbol, Symbol other) {
        if (!getSymbols().contains(symbol) || !getSymbols().contains(other)) {
            throw new IllegalArgumentException("Illegal symbol supplied");
        }

        if (symbol.equals(other)) {
            return SymbolComparisonResult.EQUAL;
        }

        List<Symbol> beatenByFirstSymbolList = symbolRelations.get(symbol);
        if (beatenByFirstSymbolList.contains(other)) {
            return SymbolComparisonResult.BIGGER;
        }

        List<Symbol> beatenBySecondSymbolList = symbolRelations.get(other);
        if (beatenBySecondSymbolList.contains(symbol)) {
            return SymbolComparisonResult.SMALLER;
        }

        return SymbolComparisonResult.UNDEFINED;
    }
}
