package my.projects.rockpaperscissors.view;

import java.util.List;

public interface GameView {
    void updateUI(int playerScore, int computerScore, String gameOutcome, String computerChoice);
    void initUIButtons(List<String> symbolStrings);
    String getStringWithId(int resId);
}
