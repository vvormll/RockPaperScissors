package my.projects.rockpaperscissors.strategy.picker;

import org.junit.Test;

import my.projects.rockpaperscissors.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

import static org.junit.Assert.assertEquals;

public class StrategyPickerBuilderTest {

    @Test
    public void buildsDefaultCircularStrategyPicker() {
        RuleSet ruleSet = new CircularRPSRuleSet();
        StrategyPicker strategyPicker = StrategyPickerBuilder.buildDefaultCircularStrategyPicker(ruleSet.getSymbols());

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

    @Test
    public void buildRandomSymbolPickingStrategyOnlyStrategyPicker() {
        RuleSet ruleSet = new CircularRPSRuleSet();
        StrategyPicker strategyPicker = StrategyPickerBuilder.buildRandomSymbolPickingStrategyOnlyStrategyPicker(ruleSet.getSymbols());

        SymbolPickingStrategy strategy;
        for (int i = 0; i < 3; i++) {
            strategy = strategyPicker.pickNextStrategy(null);
            assertEquals(RandomSymbolPickingStrategy.class, strategy.getClass());
        }
    }
}
