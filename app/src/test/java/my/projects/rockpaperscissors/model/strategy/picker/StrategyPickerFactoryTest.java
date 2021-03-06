package my.projects.rockpaperscissors.model.strategy.picker;

import org.junit.Test;

import my.projects.rockpaperscissors.model.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RockPaperScissorsRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

import static org.junit.Assert.assertEquals;

public class StrategyPickerFactoryTest {

    @Test
    public void buildsDefaultCircularStrategyPicker() {
        RuleSet ruleSet = new CircularRPSRuleSet();
        StrategyPicker strategyPicker = StrategyPickerFactory.buildDefaultRPSCircularStrategyPicker((RockPaperScissorsRuleSet) ruleSet);

        SymbolPickingStrategy strategy;
        strategy = strategyPicker.pickNextStrategy();
        assertEquals(RandomSymbolPickingStrategy.class, strategy.getClass());

        strategy = strategyPicker.pickNextStrategy();
        assertEquals(SimpleAdaptiveSymbolPickingStrategy.class, strategy.getClass());

        strategy = strategyPicker.pickNextStrategy();
        assertEquals(RandomSymbolPickingStrategy.class, strategy.getClass());

        strategy = strategyPicker.pickNextStrategy();
        assertEquals(SimpleAdaptiveSymbolPickingStrategy.class, strategy.getClass());
    }

    @Test
    public void buildRandomSymbolPickingStrategyOnlyStrategyPicker() {
        RuleSet ruleSet = new CircularRPSRuleSet();
        StrategyPicker strategyPicker = StrategyPickerFactory.buildRandomSymbolPickingStrategyOnlyStrategyPicker(ruleSet);

        SymbolPickingStrategy strategy;
        for (int i = 0; i < 3; i++) {
            strategy = strategyPicker.pickNextStrategy();
            assertEquals(RandomSymbolPickingStrategy.class, strategy.getClass());
        }
    }
}
