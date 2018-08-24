package my.projects.rockpaperscissors.model.logic.rules;

import java.util.List;

import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

public interface RuleSet {
    SymbolComparisonResult compareSymbols(Symbol symbol, Symbol other);
    List<Symbol> getSymbols();
}
