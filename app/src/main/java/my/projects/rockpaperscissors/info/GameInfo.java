package my.projects.rockpaperscissors.info;


import java.io.Serializable;

import my.projects.rockpaperscissors.logic.game.GameOutcome;
import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class GameInfo implements Serializable {
    private GameOutcome lastGameOutcome;
    private Symbol playerChoice;
    private Symbol computerChoice;
    private int playerWins;
    private int computerWins;

    // symbols must be updated together to avoid situations when only one of the latest game choices
    // gets updated and then the GameInfo object is queried for info
    public void updateInfo(Symbol playerChoice, Symbol computerChoice, GameOutcome gameOutcome) {
        this.playerChoice = playerChoice;
        this.computerChoice = computerChoice;
        lastGameOutcome = gameOutcome;
        if (lastGameOutcome.equals(GameOutcome.PLAYER_LOSS)) {
            computerWins++;
        } else if (lastGameOutcome.equals(GameOutcome.PLAYER_WIN)) {
            playerWins++;
        }
    }

    public void clearScore() {
        playerWins = 0;
        computerWins = 0;
        lastGameOutcome = null;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getComputerWins() {
        return computerWins;
    }

    public Symbol getPlayerChoice() {
        return playerChoice;
    }

    public Symbol getComputerChoice() {
        return computerChoice;
    }

    public GameOutcome getGameOutcome() {
        return lastGameOutcome;
    }

    public boolean isFirstGame() {
        return lastGameOutcome == null;
    }
}
