package my.projects.rockpaperscissors;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class RPSGameActivityTest {

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
    public void showsRockPaperScissorsButton() {
        onView(allOf(instanceOf(Button.class), withText(Symbol.ROCK.name()))).check(matches(isDisplayed()));
        onView(allOf(instanceOf(Button.class), withText(Symbol.PAPER.name()))).check(matches(isDisplayed()));
        onView(allOf(instanceOf(Button.class), withText(Symbol.SCISSORS.name()))).check(matches(isDisplayed()));
    }

    @Test
    public void showsScoreFields() {
        onView(withId(R.id.playerScoreTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.computerScoreTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void showsComputerChoiceAfterPickingASymbol() {
        onView(withId(R.id.computerActionTextView))
                .check(matches(allOf(isDisplayed(), withText(getString(R.string.none)))));

        onView(allOf(instanceOf(Button.class), withText(Symbol.ROCK.name()))).perform(click());

        onView(withId(R.id.computerActionTextView))
                .check(matches(allOf(isDisplayed(), not(withText(getString(R.string.none))))));
    }

    private String getString(int id) {
        return gameActivityIntentsTestRule.getActivity().getString(id);
    }

}
