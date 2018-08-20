package my.projects.rockpaperscissors;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class GameActivity extends Activity implements GameView {

    public static final String GAME_INFO_KEY = "gameinfo";

    private GamePresenter gamePresenter;

    private TextView playerScoreTextView;
    private TextView computerScoreTextView;
    private TextView computerActionTextView;
    private TextView stateTextView;

    private HorizontalScrollView buttonsScrollView;
    private LinearLayout buttonsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gamePresenter = new GamePresenter();

        if (savedInstanceState != null) {
            gamePresenter.onRestoreState((GameInfo) savedInstanceState.getSerializable(GAME_INFO_KEY));
        }

        buttonsScrollView = findViewById(R.id.buttonsScrollView);
        buttonsLinearLayout = findViewById(R.id.buttonLinearLayout);

        playerScoreTextView = findViewById(R.id.playerScoreTextView);
        computerScoreTextView = findViewById(R.id.computerScoreTextView);
        computerActionTextView = findViewById(R.id.computerActionTextView);
        stateTextView = findViewById(R.id.stateTextView);
    }

    @Override
    public void updateUI(int playerScore, int computerScore, String gameOutcome, String computerChoice) {
        playerScoreTextView.setText(getString(R.string.player_score, playerScore));
        computerScoreTextView.setText(getString(R.string.computer_score, computerScore));
        stateTextView.setText(gameOutcome);
        computerActionTextView.setText(computerChoice);
    }

    @Override
    public void initUI(List<String> symbolStrings) {
        buttonsLinearLayout.removeAllViews();

        int id = 0;
        for (String symbolString : symbolStrings) {
            final Button button = new Button(this);

            button.setId(id);
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            button.setText(symbolString);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gamePresenter.onPickedSymbol(Symbol.valueOf(button.getText().toString()));
                }
            });

            buttonsLinearLayout.addView(button);

            id++;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        gamePresenter.onAttachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gamePresenter.onDetachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gamePresenter.onStartedGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            gamePresenter.onQuitGame();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(GAME_INFO_KEY, gamePresenter.getGameInfo());
        super.onSaveInstanceState(outState);
    }
}
