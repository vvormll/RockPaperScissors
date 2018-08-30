package my.projects.rockpaperscissors.model.strategy.picker;

import my.projects.rockpaperscissors.model.logic.rules.RockPaperScissorsRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.model.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyPickerFactory {

    public static StrategyPicker buildDefaultRPSCircularStrategyPicker(RockPaperScissorsRuleSet ruleSet) {
        List<SymbolPickingStrategy> list = new ArrayList<>();
        list.add(new RandomSymbolPickingStrategy(ruleSet));
        list.add(new SimpleAdaptiveSymbolPickingStrategy(ruleSet));

        return new CircularStrategyPicker(list);
    }

    public static StrategyPicker buildRandomSymbolPickingStrategyOnlyStrategyPicker(RuleSet ruleSet) {
        return new SingleStrategyPicker(new RandomSymbolPickingStrategy(ruleSet));
    }
}
