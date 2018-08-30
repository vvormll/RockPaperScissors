package my.projects.rockpaperscissors.model.strategy;

import org.junit.Before;
import org.junit.Test;

import my.projects.rockpaperscissors.model.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;

public class RandomSymbolPickingStrategyTest {

    private RandomSymbolPickingStrategy strategy;

    @Before
    public void setUp() {
        RuleSet ruleSet = new CircularRPSRuleSet();
        strategy = new RandomSymbolPickingStrategy(ruleSet);
    }

    @Test
    public void picksSymbol() {
        Symbol symbol = strategy.pickSymbol();

        assertEquals(false, symbol == null);
    }

    @Test
    public void isItTimeToChangeStrategyReturnsFalseBeforeGameCountThreshold() {
        for (int i = 0; i < strategy.getGameCountThreshold(); i++) {
            strategy.update(null);
        }

        assertEquals(false, strategy.isItTimeToChangeStrategy());

    }

    @Test
    public void isItTimeToChangeStrategyReturnsTrueAfterGameCountThreshold() {
        for (int i = 0; i < strategy.getGameCountThreshold() + 1; i++) {
            strategy.update(null);
        }

        assertEquals(true, strategy.isItTimeToChangeStrategy());

    }
}
