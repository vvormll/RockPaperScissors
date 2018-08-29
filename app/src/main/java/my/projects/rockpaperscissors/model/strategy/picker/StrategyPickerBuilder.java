package my.projects.rockpaperscissors.model.strategy.picker;

import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.model.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyPickerBuilder {

    public static StrategyPicker buildDefaultCircularStrategyPicker(List<Symbol> symbolSet) {
        List<SymbolPickingStrategy> list = new ArrayList<>();
        list.add(new RandomSymbolPickingStrategy(symbolSet));
        list.add(new SimpleAdaptiveSymbolPickingStrategy(symbolSet));

        return new CircularStrategyPicker(list);
    }

    public static StrategyPicker buildRandomSymbolPickingStrategyOnlyStrategyPicker(List<Symbol> symbolSet) {
        return new SingleStrategyPicker(new RandomSymbolPickingStrategy(symbolSet));
    }
}
