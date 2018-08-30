package my.projects.rockpaperscissors.model.strategy;

import org.junit.Before;
import org.junit.Test;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.game.GameOutcome;
import my.projects.rockpaperscissors.model.logic.rules.CircularRPSRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RockPaperScissorsRuleSet;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleAdaptiveSymbolPickingStrategyTest {

    private SimpleAdaptiveSymbolPickingStrategy strategy;

    @Before
    public void setUp() {
        RuleSet ruleSet = new CircularRPSRuleSet();
        strategy = new SimpleAdaptiveSymbolPickingStrategy((RockPaperScissorsRuleSet) ruleSet);
    }

    @Test
    public void changesSymbolAfterPlayerWin() {
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
