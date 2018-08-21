package my.projects.rockpaperscissors.strategy.picker;

import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyPickerBuilder {

    public static StrategyPicker buildDefaultCircularStrategyPicker(RuleSet ruleSet) {
        List<SymbolPickingStrategy> list = new ArrayList<>();
        list.add(new RandomSymbolPickingStrategy(ruleSet.getSymbols()));
        list.add(new SimpleAdaptiveSymbolPickingStrategy(ruleSet.getSymbols()));

        return new CircularStrategyPicker(list);
    }
}
