package my.projects.rockpaperscissors;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class GameActivity extends Activity implements GameView {

    public static final String GAME_INFO_KEY = "gameinfo";

    private GamePresenter gamePresenter;

    private TextView playerScoreTextView;
    private TextView computerScoreTextView;
    private TextView computerActionTextView;
    private TextView stateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gamePresenter = new GamePresenter(this);
        gamePresenter.setGameController(new GameController());

        if (savedInstanceState != null) {
            gamePresenter.onRestoreState((GameInfo) savedInstanceState.getSerializable(GAME_INFO_KEY));
        }

        playerScoreTextView = findViewById(R.id.playerScoreTextView);
        computerScoreTextView = findViewById(R.id.computerScoreTextView);
        computerActionTextView = findViewById(R.id.computerActionTextView);
        stateTextView = findViewById(R.id.stateTextView);

        Button rockButton = findViewById(R.id.rockButton);
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePresenter.onPickedSymbol(Symbol.ROCK);
            }
        });

        Button paperButton = findViewById(R.id.scissorsButton);
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePresenter.onPickedSymbol(Symbol.PAPER);
            }
        });

        Button scissorsButton = findViewById(R.id.paperButton);
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePresenter.onPickedSymbol(Symbol.SCISSORS);
            }
        });
    }

    @Override
    public void updateUI(int playerScore, int computerScore, String gameOutcome, String computerChoice) {
        playerScoreTextView.setText(getString(R.string.player_score, playerScore));
        computerScoreTextView.setText(getString(R.string.computer_score, computerScore));
        stateTextView.setText(gameOutcome);
        computerActionTextView.setText(computerChoice);
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
