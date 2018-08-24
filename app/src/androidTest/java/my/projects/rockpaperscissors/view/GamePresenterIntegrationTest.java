package my.projects.rockpaperscissors.view;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.presenter.GamePresenterFactory;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
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
            GamePresenterFactory.setTestMode(true);
            super.beforeActivityLaunched();
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
    public void clickOnSymbolButtonCallsOnPlayerPicked() {
        onView(allOf(instanceOf(Button.class), withText(Symbol.ROCK.name()))).perform(click());

        assertTrue(testGamePresenter.onPickedSymbolCalled);
    }

}
