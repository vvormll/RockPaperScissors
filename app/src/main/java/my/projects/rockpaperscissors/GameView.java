package my.projects.rockpaperscissors;

import java.util.List;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public interface GameView {
    void updateUI(int playerScore, int computerScore, String gameOutcome, String computerChoice);
    void initUI(List<String> symbolStrings);
    String getStringWithId(int resId);
}
