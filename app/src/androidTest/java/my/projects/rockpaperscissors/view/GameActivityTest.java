package my.projects.rockpaperscissors.view;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import my.projects.rockpaperscissors.R;
import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;
import my.projects.rockpaperscissors.view.GameActivity;
import my.projects.rockpaperscissors.view.GameView;
import my.projects.rockpaperscissors.view.PickGameModeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class GameActivityTest {

    @Rule
    public IntentsTestRule<GameActivity> gameActivityIntentsTestRule = new IntentsTestRule<GameActivity>(GameActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra(PickGameModeActivity.GAME_MODE_KEY, GameMode.ROCK_PAPER_SCISSORS);
            return intent;
        }
    };

    @Test
    public void showsScoreFields() {
        onView(ViewMatchers.withId(R.id.playerScoreTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.computerScoreTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void showsComputerChoiceTextView() {
        onView(withId(R.id.computerActionTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void showsStateTextView() {
        onView(withId(R.id.stateTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void showsComputerChoiceAfterPickingASymbol() {
        onView(withId(R.id.computerActionTextView))
                .check(matches(allOf(isDisplayed(), withText(getString(R.string.none)))));

        onView(allOf(instanceOf(Button.class), withText(Symbol.ROCK.name()))).perform(click());

        onView(withId(R.id.computerActionTextView))
                .check(matches(allOf(isDisplayed(), not(withText(getString(R.string.none))))));
    }

    @Test
    public void initUIInitsUIWithButtons() {
        final GameView gameView = gameActivityIntentsTestRule.getActivity();

        gameActivityIntentsTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.initUIButtons(Arrays.asList("BUTTON", "OTHER_BUTTON"));
            }
        });

        onView(allOf(withText("BUTTON"), instanceOf(Button.class))).check(matches(isDisplayed()));
        onView(allOf(withText("OTHER_BUTTON"), instanceOf(Button.class))).check(matches(isDisplayed()));

    }

    //shortcut methods

    private String getString(int id) {
        return gameActivityIntentsTestRule.getActivity().getString(id);
    }

    private String getString(int id, Object... args) {
        return gameActivityIntentsTestRule.getActivity().getString(id, args);
    }
}
