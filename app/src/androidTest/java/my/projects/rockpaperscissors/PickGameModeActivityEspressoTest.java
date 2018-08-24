package my.projects.rockpaperscissors;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.view.PickGameModeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class PickGameModeActivityEspressoTest {

    @Rule
    public IntentsTestRule<PickGameModeActivity> activityIntentsTestRule = new IntentsTestRule<>(PickGameModeActivity.class);

    @Test
    public void showsGameModeButtons() {
        onView(withId(R.id.rpsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.rpslsButton)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnRPSButtonSendIntentWithRPSGameMode() {
        onView(withId(R.id.rpsButton)).perform(click());

        intended(allOf(hasComponent(hasShortClassName(".view.GameActivity")), hasExtra(PickGameModeActivity.GAME_MODE_KEY, GameMode.ROCK_PAPER_SCISSORS)));
    }

    @Test
    public void clickOnRPSLSButtonSendIntentWithRPSLSGameMode() {
        onView(withId(R.id.rpslsButton)).perform(click());

        intended(allOf(hasComponent(hasShortClassName(".view.GameActivity")), hasExtra(PickGameModeActivity.GAME_MODE_KEY, GameMode.ROCK_PAPER_SCISSORS_LIZARD_SPOCK)));
    }
}
