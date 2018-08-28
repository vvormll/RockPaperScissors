package my.projects.rockpaperscissors.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.game.GameOutcome;
import my.projects.rockpaperscissors.model.logic.game.VictoryConditionChecker;
import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.model.logic.symbol.SymbolPicker;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;
import my.projects.rockpaperscissors.model.strategy.picker.StrategyPicker;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    private GameController gameController;

    private RuleSet mockRuleSet;
    private SymbolPicker mockSymbolPicker;
    private StrategyPicker mockStrategyPicker;
    private GameInfo mockGameInfo;
    private VictoryConditionChecker mockVictoryConditionChecker;

    @Before
    public void setUp() {
        mockVictoryConditionChecker = mock(VictoryConditionChecker.class);
        when(mockVictoryConditionChecker.checkOutcome(ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<Symbol>any())).thenReturn(GameOutcome.PLAYER_WIN);

        mockSymbolPicker = mock(SymbolPicker.class);
        when(mockSymbolPicker.shouldChangeStrategy()).thenReturn(false);

        mockGameInfo = mock(GameInfo.class);

        mockStrategyPicker = mock(StrategyPicker.class);
        mockRuleSet = mock(RuleSet.class);

        gameController = new GameController(mockGameInfo, mockVictoryConditionChecker, mockStrategyPicker, mockSymbolPicker, mockRuleSet);

    }

    @Test
    public void symbolPickerIsQueriedOnPlayerInput() {
        gameController.onPlayerInput(Symbol.ROCK);

        verify(mockSymbolPicker).pickSymbol(ArgumentMatchers.<GameInfo>any());
    }

    @Test
    public void checkOutcomeIsCalledOnPlayerInput() {
        gameController.onPlayerInput(Symbol.ROCK);

        verify(mockVictoryConditionChecker).checkOutcome(ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<Symbol>any());
    }

    @Test
    public void gameInfoIsUpdatedOnPlayerInput() {
        gameController.onPlayerInput(Symbol.ROCK);

        verify(mockGameInfo).updateInfo(ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<Symbol>any(), ArgumentMatchers.<GameOutcome>any());
    }

    @Test
    public void picksNextStrategyIfNecessaryOnPlayerInput() {
        when(mockSymbolPicker.shouldChangeStrategy()).thenReturn(true);

        gameController.onPlayerInput(Symbol.ROCK);

        verify(mockSymbolPicker).shouldChangeStrategy();
        verify(mockStrategyPicker).pickNextStrategy(ArgumentMatchers.<SymbolPickingStrategy>any());
        verify(mockSymbolPicker).changeStrategy(ArgumentMatchers.<SymbolPickingStrategy>any());
    }

    @Test
    public void getSymbolsQueriesRuleSet() {
        gameController.getSymbols();

        verify(mockRuleSet).getSymbols();
    }

}
