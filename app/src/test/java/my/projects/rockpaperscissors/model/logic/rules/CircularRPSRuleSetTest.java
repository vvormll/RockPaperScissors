package my.projects.rockpaperscissors.model.logic.rules;

import org.junit.Test;

import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;

public class CircularRPSRuleSetTest {

    @Test
    public void comparisonsAreCorrect() {
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
