package my.projects.rockpaperscissors.model.logic.rules;

import org.junit.Test;

import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;

public class MapRPSLSRuleSetTest {

    @Test
    public void comparisonsAreCorrect() {
        RuleSet rs = new MapRPSLSRuleSet();

        assertEquals(SymbolComparisonResult.EQUAL, rs.compareSymbols(Symbol.ROCK, Symbol.ROCK));
        assertEquals(SymbolComparisonResult.EQUAL, rs.compareSymbols(Symbol.PAPER, Symbol.PAPER));
        assertEquals(SymbolComparisonResult.EQUAL, rs.compareSymbols(Symbol.SCISSORS, Symbol.SCISSORS));
        assertEquals(SymbolComparisonResult.EQUAL, rs.compareSymbols(Symbol.LIZARD, Symbol.LIZARD));
        assertEquals(SymbolComparisonResult.EQUAL, rs.compareSymbols(Symbol.SPOCK, Symbol.SPOCK));

        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.PAPER, Symbol.ROCK));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.ROCK, Symbol.PAPER));
        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.PAPER, Symbol.SPOCK));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.SPOCK, Symbol.PAPER));

        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.SCISSORS, Symbol.PAPER));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.PAPER, Symbol.SCISSORS));
        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.SCISSORS, Symbol.LIZARD));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.LIZARD, Symbol.SCISSORS));

        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.ROCK, Symbol.SCISSORS));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.SCISSORS, Symbol.ROCK));
        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.ROCK, Symbol.LIZARD));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.LIZARD, Symbol.ROCK));

        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.LIZARD, Symbol.SPOCK));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.SPOCK, Symbol.LIZARD));
        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.LIZARD, Symbol.PAPER));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.PAPER, Symbol.LIZARD));

        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.SPOCK, Symbol.SCISSORS));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.SCISSORS, Symbol.SPOCK));
        assertEquals(SymbolComparisonResult.BIGGER, rs.compareSymbols(Symbol.SPOCK, Symbol.ROCK));
        assertEquals(SymbolComparisonResult.SMALLER, rs.compareSymbols(Symbol.ROCK, Symbol.SPOCK));


    }

}
