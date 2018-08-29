package my.projects.rockpaperscissors.model.logic.rules;

import java.util.Iterator;
import java.util.List;

import my.projects.rockpaperscissors.model.util.CircularIterator;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

public class CircularRPSRuleSet extends RockPaperScissorsRuleSet {

    @Override
    public SymbolComparisonResult compareSymbols(Symbol symbol, Symbol other) {
        if (symbol.equals(other)) {
            return SymbolComparisonResult.EQUAL;
        }

        List<Symbol> symbols = getSymbols();

        Iterator<Symbol> circularIterator = CircularIterator.build(symbols);
        Symbol prev, next;

        next = circularIterator.next();
        for (int i = 1; i < symbols.size() + 1; i++) {
            prev = next;
            next = circularIterator.next();

            if (prev.equals(symbol) && next.equals(other)) {
                return SymbolComparisonResult.SMALLER;
            } else if (prev.equals(other) && next.equals(symbol)) {
                return SymbolComparisonResult.BIGGER;
            }
        }

        return SymbolComparisonResult.UNDEFINED;
    }
}
