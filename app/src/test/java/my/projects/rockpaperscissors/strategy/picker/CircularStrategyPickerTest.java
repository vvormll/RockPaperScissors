package my.projects.rockpaperscissors.strategy.picker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import my.projects.rockpaperscissors.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

import static org.junit.Assert.assertEquals;

public class CircularStrategyPickerTest {

    @Test
    public void circlesAroundStrategies() {
        RuleSet ruleSet = new CircularRPSRuleSet();

        List<SymbolPickingStrategy> strategyList = new ArrayList<>();
        strategyList.add(new RandomSymbolPickingStrategy(ruleSet.getSymbols()));
        strategyList.add(new SimpleAdaptiveSymbolPickingStrategy(ruleSet.getSymbols()));

        CircularStrategyPicker strategyPicker = new CircularStrategyPicker(strategyList);

        SymbolPickingStrategy strategy;
        strategy = strategyPicker.pickNextStrategy(null);
        assertEquals(RandomSymbolPickingStrategy.class, strategy.getClass());

        strategy = strategyPicker.pickNextStrategy(null);
        assertEquals(SimpleAdaptiveSymbolPickingStrategy.class, strategy.getClass());

        strategy = strategyPicker.pickNextStrategy(null);
        assertEquals(RandomSymbolPickingStrategy.class, strategy.getClass());

        strategy = strategyPicker.pickNextStrategy(null);
        assertEquals(SimpleAdaptiveSymbolPickingStrategy.class, strategy.getClass());
    }
}
