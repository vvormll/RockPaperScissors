package my.projects.rockpaperscissors.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.Button;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.projects.rockpaperscissors.R;
import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.model.logic.game.GameOutcome;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.presenter.GamePresenterFactory;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class GamePresenterIntegrationTest {

    private GamePresenterFactory.TestGamePresenterImpl testGamePresenter;

    @Rule
    public ActivityTestRule<GameActivity> gameActivityActivityTestRule = new ActivityTestRule<GameActivity>(GameActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra(PickGameModeActivity.GAME_MODE_KEY, GameMode.ROCK_PAPER_SCISSORS);
            return intent;
        }

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            GamePresenterFactory.setTestMode(true);
        }

        @Override
        protected void afterActivityFinished() {
            GamePresenterFactory.setTestMode(false);
            super.afterActivityFinished();
        }
    };

    @Before
    public void setUp() {
        testGamePresenter = (GamePresenterFactory.TestGamePresenterImpl) gameActivityActivityTestRule.getActivity().getGamePresenter();
    }

    @Test
    public void onAttachViewCalled() {
        assertTrue(testGamePresenter.onAttachViewCalled);
    }

    @Test
    public void onStartedGameCalled() {
        assertTrue(testGamePresenter.onStartedGameCalled);
    }

    @Test
    public void uiStateIsRestoredAfterConfigurationChange() {
        final GameInfo gameInfo = new GameInfo();
        gameInfo.updateInfo(Symbol.PAPER, Symbol.ROCK, GameOutcome.PLAYER_WIN);
        gameInfo.updateInfo(Symbol.PAPER, Symbol.ROCK, GameOutcome.PLAYER_WIN);
        gameInfo.updateInfo(Symbol.PAPER, Symbol.ROCK, GameOutcome.PLAYER_WIN);
        gameInfo.updateInfo(Symbol.ROCK, Symbol.PAPER, GameOutcome.PLAYER_LOSS);
        gameInfo.updateInfo(Symbol.ROCK, Symbol.PAPER, GameOutcome.PLAYER_LOSS);
        testGamePresenter.setMockGameInfo(gameInfo);

        gameActivityActivityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameActivityActivityTestRule.getActivity().updateUI(3, 2, GameOutcome.PLAYER_LOSS.toString(), Symbol.PAPER.name());
            }
        });

        gameActivityActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.playerScoreTextView)).check(matches(withText(getString(R.string.player_score, 3))));
        onView(withId(R.id.computerScoreTextView)).check(matches(withText(getString(R.string.computer_score, 2))));
        onView(withId(R.id.computerActionTextView)).check(matches(withText(Symbol.PAPER.name())));
        onView(withId(R.id.stateTextView)).check(matches(withText(GameOutcome.PLAYER_LOSS.toString())));

        gameInfo.updateInfo(Symbol.PAPER, Symbol.SCISSORS, GameOutcome.PLAYER_LOSS);
        gameActivityActivityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameActivityActivityTestRule.getActivity().updateUI(3, 3, GameOutcome.PLAYER_LOSS.toString(), Symbol.SCISSORS.name());
            }
        });

        gameActivityActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.playerScoreTextView)).check(matches(withText(getString(R.string.player_score, 3))));
        onView(withId(R.id.computerScoreTextView)).check(matches(withText(getString(R.string.computer_score, 3))));
        onView(withId(R.id.computerActionTextView)).check(matches(withText(Symbol.SCISSORS.name())));
        onView(withId(R.id.stateTextView)).check(matches(withText(GameOutcome.PLAYER_LOSS.toString())));
    }

    @Test
    public void clickOnSymbolButtonCallsOnPlayerPicked() {
        onView(allOf(instanceOf(Button.class), withText(Symbol.ROCK.name()))).perform(click());

        assertTrue(testGamePresenter.onPickedSymbolCalled);
    }

    //shortcut methods

    private String getString(int id) {
        return gameActivityActivityTestRule.getActivity().getString(id);
    }

    private String getString(int id, Object... args) {
        return gameActivityActivityTestRule.getActivity().getString(id, args);
    }

}
