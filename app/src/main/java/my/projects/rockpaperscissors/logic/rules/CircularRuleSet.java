package my.projects.rockpaperscissors.logic.rules;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import my.projects.rockpaperscissors.logic.CircularIteratorBuilder;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class CircularRuleSet implements RuleSet {

    private Set<Symbol> symbolSet = new LinkedHashSet<>();

    @Override
    public SymbolComparisonResult compareSymbols(Symbol symbol, Symbol other) {
        if (symbolSet.size() < 2) {
            throw new IllegalStateException("RuleSet has too few elements (" + symbolSet.size() + ")");
        }

        if (symbol.equals(other)) {
            return SymbolComparisonResult.EQUAL;
        }

        Iterator<Symbol> circularIterator = CircularIteratorBuilder.buildCircularIterator(symbolSet);
        Symbol prev, next;

        next = circularIterator.next();
        for (int i = 1; i < symbolSet.size() + 1; i++) {
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

    @Override
    public List<Symbol> getSymbols() {
        return new LinkedList<>(symbolSet);
    }

    public void addSymbolToEnd(Symbol symbol) {
        symbolSet.add(symbol);
    }

    public static void main(String[] args) {
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
