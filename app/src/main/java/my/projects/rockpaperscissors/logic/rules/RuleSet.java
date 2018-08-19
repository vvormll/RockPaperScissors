package my.projects.rockpaperscissors.logic.rules;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

public interface RuleSet {
    SymbolComparisonResult compareSymbols(Symbol symbol, Symbol other);
}
