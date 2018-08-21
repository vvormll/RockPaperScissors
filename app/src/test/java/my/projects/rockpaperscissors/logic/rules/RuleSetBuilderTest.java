package my.projects.rockpaperscissors.logic.rules;

import org.junit.Test;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;

public class RuleSetBuilderTest {

    @Test
    public void buildDefaultRPSRuleSet() {
        RuleSet rps = new CircularRPSRuleSet();

        assertEquals(SymbolComparisonResult.BIGGER, rps.compareSymbols(Symbol.PAPER, Symbol.ROCK));
        assertEquals(SymbolComparisonResult.SMALLER, rps.compareSymbols(Symbol.ROCK, Symbol.PAPER));
        assertEquals(SymbolComparisonResult.BIGGER, rps.compareSymbols(Symbol.SCISSORS, Symbol.PAPER));
        assertEquals(SymbolComparisonResult.SMALLER, rps.compareSymbols(Symbol.PAPER, Symbol.SCISSORS));
        assertEquals(SymbolComparisonResult.BIGGER, rps.compareSymbols(Symbol.ROCK, Symbol.SCISSORS));
        assertEquals(SymbolComparisonResult.SMALLER, rps.compareSymbols(Symbol.SCISSORS, Symbol.ROCK));
        assertEquals(SymbolComparisonResult.EQUAL, rps.compareSymbols(Symbol.ROCK, Symbol.ROCK));
    }
}
