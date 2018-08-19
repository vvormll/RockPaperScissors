package my.projects.rockpaperscissors.logic.rules;

import org.junit.Before;
import org.junit.Test;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class CircularRuleSetTest {

    @Test
    public void comparisonsAreCorrect() {
        CircularRuleSet circularRuleSet = new CircularRuleSet();
        circularRuleSet.addSymbolToEnd(Symbol.ROCK);
        circularRuleSet.addSymbolToEnd(Symbol.PAPER);
        circularRuleSet.addSymbolToEnd(Symbol.SCISSORS);

        compare(circularRuleSet, Symbol.ROCK, Symbol.PAPER);
        compare(circularRuleSet, Symbol.PAPER, Symbol.ROCK);
        compare(circularRuleSet, Symbol.PAPER, Symbol.SCISSORS);
        compare(circularRuleSet, Symbol.SCISSORS, Symbol.PAPER);
        compare(circularRuleSet, Symbol.ROCK, Symbol.SCISSORS);
        compare(circularRuleSet, Symbol.SCISSORS, Symbol.ROCK);
        compare(circularRuleSet, Symbol.ROCK, Symbol.ROCK);
    }

    private static void compare(CircularRuleSet circularRuleSet, Symbol symbol, Symbol other) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(symbol.name());
        stringBuilder.append(" is ");
        stringBuilder.append(circularRuleSet.compareSymbols(symbol, other).name());
        stringBuilder.append(" than ");
        stringBuilder.append(other.name());
        System.out.println(stringBuilder.toString());
    }
}
