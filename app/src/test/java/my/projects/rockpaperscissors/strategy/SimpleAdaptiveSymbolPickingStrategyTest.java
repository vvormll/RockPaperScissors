package my.projects.rockpaperscissors.strategy;

import org.junit.Test;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.game.GameOutcome;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleAdaptiveSymbolPickingStrategyTest {
    //TODO: implement

    @Test
    public void changesSymbolAfterPlayerWin() {
        SimpleAdaptiveSymbolPickingStrategy strategy = new SimpleAdaptiveSymbolPickingStrategy();

        GameInfo mockGameInfo = mock(GameInfo.class);
        when(mockGameInfo.isFirstGame()).thenReturn(false);
        when(mockGameInfo.getGameOutcome()).thenReturn(GameOutcome.PLAYER_WIN);

        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.ROCK);
        strategy.update(mockGameInfo);
        assertEquals(Symbol.PAPER, strategy.pickSymbol());

        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.PAPER);
        strategy.update(mockGameInfo);
        assertEquals(Symbol.SCISSORS, strategy.pickSymbol());

        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.SCISSORS);
        strategy.update(mockGameInfo);
        assertEquals(Symbol.ROCK, strategy.pickSymbol());
    }

    @Test
    public void picksPlayersSymbolAfterPlayerLoss() {
        SimpleAdaptiveSymbolPickingStrategy strategy = new SimpleAdaptiveSymbolPickingStrategy();

        GameInfo mockGameInfo = mock(GameInfo.class);
        when(mockGameInfo.isFirstGame()).thenReturn(false);
        when(mockGameInfo.getGameOutcome()).thenReturn(GameOutcome.PLAYER_LOSS);

        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.ROCK);
        strategy.update(mockGameInfo);
        assertEquals(Symbol.ROCK, strategy.pickSymbol());

        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.PAPER);
        strategy.update(mockGameInfo);
        assertEquals(Symbol.PAPER, strategy.pickSymbol());

        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.SCISSORS);
        strategy.update(mockGameInfo);
        assertEquals(Symbol.SCISSORS, strategy.pickSymbol());
    }

    @Test
    public void isItTimeToChangeStrategyReturnsTrueBeforeCrossingConsecutiveLossesThreshold() {
        SimpleAdaptiveSymbolPickingStrategy strategy = new SimpleAdaptiveSymbolPickingStrategy();

        GameInfo mockGameInfo = mock(GameInfo.class);
        when(mockGameInfo.isFirstGame()).thenReturn(false);
        when(mockGameInfo.getGameOutcome()).thenReturn(GameOutcome.PLAYER_WIN);
        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.ROCK);

        for (int i = 0; i < strategy.getConsecutiveLossesThreshold(); i++) {
            strategy.update(mockGameInfo);
        }

        assertEquals(false, strategy.isItTimeToChangeStrategy());
    }

    @Test
    public void isItTimeToChangeStrategyReturnsTrueAfterCrossingConsecutiveLossesThreshold() {
        SimpleAdaptiveSymbolPickingStrategy strategy = new SimpleAdaptiveSymbolPickingStrategy();

        GameInfo mockGameInfo = mock(GameInfo.class);
        when(mockGameInfo.isFirstGame()).thenReturn(false);
        when(mockGameInfo.getGameOutcome()).thenReturn(GameOutcome.PLAYER_WIN);
        when(mockGameInfo.getPlayerChoice()).thenReturn(Symbol.ROCK);

        for (int i = 0; i < strategy.getConsecutiveLossesThreshold() + 1; i++) {
            strategy.update(mockGameInfo);
        }

        assertEquals(true, strategy.isItTimeToChangeStrategy());
    }
}
