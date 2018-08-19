package my.projects.rockpaperscissors.strategy.picker;

import org.junit.Test;

import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

import static org.junit.Assert.assertEquals;

public class StrategyPickerBuilderTest {

    @Test
    public void buildsDefaultCircularStrategyPicker() {
        StrategyPicker strategyPicker = StrategyPickerBuilder.buildDefaultCircularStrategyPicker();

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
