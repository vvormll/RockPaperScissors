package my.projects.rockpaperscissors.strategy;

import org.junit.Test;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;

public class RandomSymbolPickingStrategyTest {

    @Test
    public void picksSymbol() {
        RandomSymbolPickingStrategy strategy = new RandomSymbolPickingStrategy();
        Symbol symbol = strategy.pickSymbol();

        assertEquals(false, symbol == null);
    }

    @Test
    public void isItTimeToChangeStrategyReturnsFalseBeforeGameCountThreshold() {
        RandomSymbolPickingStrategy strategy = new RandomSymbolPickingStrategy();

        for (int i = 0; i < strategy.getGameCountThreshold(); i++) {
            strategy.update(null);
        }

        assertEquals(false, strategy.isItTimeToChangeStrategy());

    }

    @Test
    public void isItTimeToChangeStrategyReturnsTrueAfterGameCountThreshold() {
        RandomSymbolPickingStrategy strategy = new RandomSymbolPickingStrategy();
        for (int i = 0; i < strategy.getGameCountThreshold() + 1; i++) {
            strategy.update(null);
        }

        assertEquals(true, strategy.isItTimeToChangeStrategy());

    }
}
