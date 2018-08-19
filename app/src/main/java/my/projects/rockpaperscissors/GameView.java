package my.projects.rockpaperscissors;

import my.projects.rockpaperscissors.info.GameInfo;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public interface GameView {
    void updateUI(int playerScore, int computerScore, String gameOutcome, String computerChoice);
}
