package my.projects.rockpaperscissors.logic.game;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import my.projects.rockpaperscissors.logic.rules.RuleSet;
import my.projects.rockpaperscissors.logic.rules.SymbolComparisonResult;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VictoryConditionCheckerTest {

    private VictoryConditionChecker checker;

    @Test
    public void translatesPlayersBiggerSymbolToPlayerWin() {
        RuleSet mockRuleSet = mock(RuleSet.class);
        when(mockRuleSet.compareSymbols(ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<Symbol>any())).thenReturn(SymbolComparisonResult.BIGGER);

        checker = new VictoryConditionChecker(mockRuleSet);

        assertEquals(GameOutcome.PLAYER_WIN, checker.checkOutcome(null, null));
    }

    @Test
    public void translatesPlayersSmallerSymbolToPlayerWin() {
        RuleSet mockRuleSet = mock(RuleSet.class);
        when(mockRuleSet.compareSymbols(ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<Symbol>any())).thenReturn(SymbolComparisonResult.SMALLER);

        checker = new VictoryConditionChecker(mockRuleSet);

        assertEquals(GameOutcome.PLAYER_LOSS, checker.checkOutcome(null, null));
    }

    @Test
    public void translatesSameSymbolsToDraw() {
        RuleSet mockRuleSet = mock(RuleSet.class);
        when(mockRuleSet.compareSymbols(ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<Symbol>any())).thenReturn(SymbolComparisonResult.EQUAL);

        checker = new VictoryConditionChecker(mockRuleSet);

        assertEquals(GameOutcome.DRAW, checker.checkOutcome(null, null));
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionOnUndefinedComparison() {
        RuleSet mockRuleSet = mock(RuleSet.class);
        when(mockRuleSet.compareSymbols(ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<Symbol>any())).thenReturn(SymbolComparisonResult.UNDEFINED);

        checker = new VictoryConditionChecker(mockRuleSet);

        checker.checkOutcome(null, null);
    }

}
