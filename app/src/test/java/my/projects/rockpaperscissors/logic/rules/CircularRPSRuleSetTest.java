package my.projects.rockpaperscissors.logic.rules;

import org.junit.Test;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class CircularRPSRuleSetTest {

    @Test
    public void comparisonsAreCorrect() {
        CircularRPSRuleSet circularRPSRuleSet = new CircularRPSRuleSet();
        circularRPSRuleSet.addSymbolToEnd(Symbol.ROCK);
        circularRPSRuleSet.addSymbolToEnd(Symbol.PAPER);
        circularRPSRuleSet.addSymbolToEnd(Symbol.SCISSORS);

        compare(circularRPSRuleSet, Symbol.ROCK, Symbol.PAPER);
        compare(circularRPSRuleSet, Symbol.PAPER, Symbol.ROCK);
        compare(circularRPSRuleSet, Symbol.PAPER, Symbol.SCISSORS);
        compare(circularRPSRuleSet, Symbol.SCISSORS, Symbol.PAPER);
        compare(circularRPSRuleSet, Symbol.ROCK, Symbol.SCISSORS);
        compare(circularRPSRuleSet, Symbol.SCISSORS, Symbol.ROCK);
        compare(circularRPSRuleSet, Symbol.ROCK, Symbol.ROCK);
    }

    private static void compare(CircularRPSRuleSet circularRPSRuleSet, Symbol symbol, Symbol other) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(symbol.name());
        stringBuilder.append(" is ");
        stringBuilder.append(circularRPSRuleSet.compareSymbols(symbol, other).name());
        stringBuilder.append(" than ");
        stringBuilder.append(other.name());
        System.out.println(stringBuilder.toString());
    }
}
