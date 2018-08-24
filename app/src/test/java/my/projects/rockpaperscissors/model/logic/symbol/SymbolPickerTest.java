package my.projects.rockpaperscissors.model.logic.symbol;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SymbolPickerTest {

    private SymbolPicker symbolPicker;

    @Test
    public void picksSymbolFromStrategy() {
        SymbolPickingStrategy strategy = mock(SymbolPickingStrategy.class);
        when(strategy.pickSymbol()).thenReturn(Symbol.ROCK);

        symbolPicker = new SymbolPicker(strategy);
        Symbol symbol = symbolPicker.pickSymbol(null);

        assertEquals(Symbol.ROCK, symbol);
        verify(strategy).pickSymbol();
    }

    @Test
    public void pickingSymbolCallsUpdateStrategy() {
        SymbolPickingStrategy strategy = mock(SymbolPickingStrategy.class);
        when(strategy.pickSymbol()).thenReturn(Symbol.ROCK);

        symbolPicker = new SymbolPicker(strategy);
        symbolPicker.pickSymbol(null);

        verify(strategy).update(ArgumentMatchers.<GameInfo>any());
    }

    @Test
    public void queriesStrategyOnShouldChangeStrategy() {
        SymbolPickingStrategy strategy = mock(SymbolPickingStrategy.class);

        symbolPicker = new SymbolPicker(strategy);
        symbolPicker.shouldChangeStrategy();

        verify(strategy).isItTimeToChangeStrategy();
    }
}
