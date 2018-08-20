package my.projects.rockpaperscissors.logic.rules;

import java.util.List;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

public interface RuleSet {
    SymbolComparisonResult compareSymbols(Symbol symbol, Symbol other);
    List<Symbol> getSymbols();
}
