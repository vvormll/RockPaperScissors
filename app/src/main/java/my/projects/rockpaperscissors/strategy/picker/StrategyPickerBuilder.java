package my.projects.rockpaperscissors.strategy.picker;

import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.logic.symbol.Symbol;
import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StrategyPickerBuilder {

    public static StrategyPicker buildDefaultCircularStrategyPicker(List<Symbol> symbolSet) {
        List<SymbolPickingStrategy> list = new ArrayList<>();
        list.add(new RandomSymbolPickingStrategy(symbolSet));
        list.add(new SimpleAdaptiveSymbolPickingStrategy(symbolSet));

        return new CircularStrategyPicker(list);
    }

    public static StrategyPicker buildRandomSymbolPickingStrategyOnlyStrategyPicker(List<Symbol> symbolSet) {
        return new RandomSymbolStrategyOnlyStrategyPicker(symbolSet);
    }
}
