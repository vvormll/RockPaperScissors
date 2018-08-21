package my.projects.rockpaperscissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PickGameModeActivity extends Activity {

    public static final String GAME_MODE_KEY = "game_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_mode);

        Button rpsButton = findViewById(R.id.rpsButton);
        rpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickGameModeActivity.this, GameActivity.class);
                intent.putExtra(GAME_MODE_KEY, GameMode.ROCK_PAPER_SCISSORS);
                startActivity(intent);
            }
        });

        Button rpslsButton = findViewById(R.id.rpslsButton);
        rpslsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickGameModeActivity.this, GameActivity.class);
                intent.putExtra(GAME_MODE_KEY, GameMode.ROCK_PAPER_SCISSORS_LIZARD_SPOCK);
                startActivity(intent);
            }
        });
    }
}
