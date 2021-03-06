package my.projects.rockpaperscissors.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import my.projects.rockpaperscissors.R;
import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.presenter.GamePresenter;
import my.projects.rockpaperscissors.presenter.GamePresenterFactory;

public class GameActivity extends Activity implements GameView {

    public static final String GAME_INFO_KEY = "gameinfo";

    private GamePresenter gamePresenter;

    private TextView playerScoreTextView;
    private TextView computerScoreTextView;
    private TextView computerActionTextView;
    private TextView stateTextView;

    private LinearLayout buttonsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        GameMode gameMode = (GameMode) intent.getSerializableExtra(PickGameModeActivity.GAME_MODE_KEY);

        gamePresenter = GamePresenterFactory.buildGamePresenter(gameMode);

        if (savedInstanceState != null) {
            gamePresenter.onRestoreState((GameInfo) savedInstanceState.getSerializable(GAME_INFO_KEY));
        }

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
    public void initUIButtons(List<String> symbolStrings) {
        buttonsLinearLayout.removeAllViews();

        int id = 0;
        for (String symbolString : symbolStrings) {
            final Button button = buildSymbolButton(id, symbolString);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gamePresenter.onPickedSymbol(button.getText().toString());
                }
            });

            buttonsLinearLayout.addView(button);

            id++;
        }
    }

    private Button buildSymbolButton(int id, String symbolString) {
        Button button = new Button(this);

        button.setId(id);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        button.setText(symbolString);

        return button;
    }

    @Override
    public String getStringWithId(int resId) {
        return getString(resId);
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

    void setGamePresenter(GamePresenter gamePresenter) {
        if (this.gamePresenter != null && this.gamePresenter.isViewAttached()) {
            this.gamePresenter.onDetachView();
            gamePresenter.onAttachView(this);
        }
        this.gamePresenter = gamePresenter;
    }

    GamePresenter getGamePresenter() {
        return gamePresenter;
    }
}
