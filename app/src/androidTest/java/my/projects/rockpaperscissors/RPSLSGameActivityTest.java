package my.projects.rockpaperscissors;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.projects.rockpaperscissors.logic.game.GameOutcome;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class RPSLSGameActivityTest {

    @Rule
    public IntentsTestRule<GameActivity> gameActivityIntentsTestRule = new IntentsTestRule<GameActivity>(GameActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra(PickGameModeActivity.GAME_MODE_KEY, GameMode.ROCK_PAPER_SCISSORS_LIZARD_SPOCK);
            return intent;
        }
    };

    @Test
    public void showsRockPaperScissorsLizardSpockButtons() {
        onView(allOf(instanceOf(Button.class), withText(Symbol.ROCK.name()))).check(matches(isDisplayed()));
        onView(allOf(instanceOf(Button.class), withText(Symbol.PAPER.name()))).check(matches(isDisplayed()));
        onView(allOf(instanceOf(Button.class), withText(Symbol.SCISSORS.name()))).check(matches(isDisplayed()));
        onView(allOf(instanceOf(Button.class), withText(Symbol.LIZARD.name()))).check(matches(isDisplayed()));
        onView(allOf(instanceOf(Button.class), withText(Symbol.SPOCK.name()))).check(matches(isDisplayed()));
    }

    // updateUI() tests

    @Test
    public void playerScoreGetsUpdatedByUpdateUI() {
        final GameView gameView = gameActivityIntentsTestRule.getActivity();

        onView(withId(R.id.playerScoreTextView)).check(matches(withText(getString(R.string.player_score, 0))));

        gameActivityIntentsTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.updateUI(1, 0, GameOutcome.PLAYER_WIN.toString(), Symbol.ROCK.name());
            }
        });

        onView(withId(R.id.playerScoreTextView)).check(matches(withText(getString(R.string.player_score, 1))));
    }

    @Test
    public void computerScoreGetsUpdatedByUpdateUI() {
        final GameView gameView = gameActivityIntentsTestRule.getActivity();

        onView(withId(R.id.computerScoreTextView)).check(matches(withText(getString(R.string.computer_score, 0))));

        gameActivityIntentsTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.updateUI(0, 1, GameOutcome.PLAYER_LOSS.toString(), Symbol.ROCK.name());
            }
        });

        onView(withId(R.id.computerScoreTextView)).check(matches(withText(getString(R.string.computer_score, 1))));
    }

    @Test
    public void computerChoiceGetsUpdatedByUpdateUI() {
        final GameView gameView = gameActivityIntentsTestRule.getActivity();

        onView(withId(R.id.computerActionTextView)).check(matches(withText(getString(R.string.none))));

        gameActivityIntentsTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.updateUI(0, 1, GameOutcome.PLAYER_LOSS.toString(), Symbol.ROCK.name());
            }
        });

        onView(withId(R.id.computerActionTextView)).check(matches(withText(Symbol.ROCK.name())));
    }

    @Test
    public void stateTextViewGetsUpdatedByUpdateUI() {
        final GameView gameView = gameActivityIntentsTestRule.getActivity();

        //start state
        onView(withId(R.id.stateTextView)).check(matches(withText(getString(R.string.pick))));

        //player loss state
        gameActivityIntentsTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.updateUI(0, 1, GameOutcome.PLAYER_LOSS.toString(), Symbol.ROCK.name());
            }
        });
        onView(withId(R.id.stateTextView)).check(matches(withText(GameOutcome.PLAYER_LOSS.toString())));

        //player win state
        gameActivityIntentsTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.updateUI(1, 1, GameOutcome.PLAYER_WIN.toString(), Symbol.ROCK.name());
            }
        });
        onView(withId(R.id.stateTextView)).check(matches(withText(GameOutcome.PLAYER_WIN.toString())));

        //draw state
        gameActivityIntentsTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.updateUI(1, 1, GameOutcome.DRAW.toString(), Symbol.ROCK.name());
            }
        });
        onView(withId(R.id.stateTextView)).check(matches(withText(GameOutcome.DRAW.toString())));
    }

    //shortcut methods

    private String getString(int id) {
        return gameActivityIntentsTestRule.getActivity().getString(id);
    }

    private String getString(int id, Object... args) {
        return gameActivityIntentsTestRule.getActivity().getString(id, args);
    }
}
