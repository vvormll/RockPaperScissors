package my.projects.rockpaperscissors;

import java.util.List;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public interface GameView {
    void updateUI(int playerScore, int computerScore, String gameOutcome, String computerChoice);
    void initUIButtons(List<String> symbolStrings);
    String getStringWithId(int resId);
}
