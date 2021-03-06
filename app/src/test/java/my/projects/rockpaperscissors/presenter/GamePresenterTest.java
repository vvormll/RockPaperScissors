package my.projects.rockpaperscissors.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import my.projects.rockpaperscissors.R;
import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.view.GameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GamePresenterTest {

    private GamePresenter gamePresenter;
    private GameView mockGameView;

    @Before
    public void setUp() {
        gamePresenter = new GamePresenterImpl(GameMode.ROCK_PAPER_SCISSORS);
        mockGameView = mock(GameView.class);
        gamePresenter.onAttachView(mockGameView);

        when(mockGameView.getStringWithId(R.string.none)).thenReturn("NONE");
        when(mockGameView.getStringWithId(R.string.pick)).thenReturn("PICK");
    }

    @Test
    public void onStartedGameInitsGameViewUIWithSymbols() {
        gamePresenter.onStartedGame();

        verify(mockGameView).initUIButtons(ArgumentMatchers.<String>anyList());
    }

    @Test
    public void onStartedGameUpdatesUIWithStarterValues() {
        gamePresenter.onStartedGame();

        verify(mockGameView).updateUI(ArgumentMatchers.eq(0), ArgumentMatchers.eq(0), ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionWhenAttachingViewWhenViewWasAlreadyAttached() {
        gamePresenter.onAttachView(mockGameView);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionOnStartedGameWithNoAttachedView() {
        gamePresenter.onDetachView();

        gamePresenter.onStartedGame();
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionOnPickedSymbolWithNoAttachedView() {
        gamePresenter.onDetachView();

        gamePresenter.onPickedSymbol(null);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionOnDetachWithNoAttachedView() {
        gamePresenter.onDetachView();
        gamePresenter.onDetachView();
    }
}
