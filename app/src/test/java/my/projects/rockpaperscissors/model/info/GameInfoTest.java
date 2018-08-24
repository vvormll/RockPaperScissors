package my.projects.rockpaperscissors.model.info;

import org.junit.Before;
import org.junit.Test;

import my.projects.rockpaperscissors.model.logic.game.GameOutcome;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

import static org.junit.Assert.assertEquals;

public class GameInfoTest {

    private GameInfo gameInfo;

    @Before
    public void setUp() {
        gameInfo = new GameInfo();
    }

    @Test
    public void isFirstGameReturnsTrueBeforeFirstGame() {
        assertEquals(true, gameInfo.isFirstGame());
    }

    @Test
    public void isFirstGameReturnsFalseAfterFirstGame() {
        gameInfo.updateInfo(Symbol.ROCK, Symbol.PAPER, GameOutcome.PLAYER_WIN);
        assertEquals(false, gameInfo.isFirstGame());
    }

    @Test
    public void clearScoreClearsScoreAndLastOutcome() {
        gameInfo.updateInfo(Symbol.ROCK, Symbol.PAPER, GameOutcome.PLAYER_WIN);
        gameInfo.updateInfo(Symbol.ROCK, Symbol.PAPER, GameOutcome.PLAYER_WIN);
        gameInfo.updateInfo(Symbol.ROCK, Symbol.PAPER, GameOutcome.PLAYER_WIN);

        gameInfo.clearScore();

        assertEquals(0, gameInfo.getComputerWins());
        assertEquals(0, gameInfo.getPlayerWins());
        assertEquals(true, gameInfo.isFirstGame());
    }

    @Test
    public void updateInfoUpdatesScoreOnPlayerWin() {
        gameInfo.updateInfo(Symbol.ROCK, Symbol.PAPER, GameOutcome.PLAYER_WIN);

        assertEquals(1, gameInfo.getPlayerWins());
        assertEquals(0, gameInfo.getComputerWins());
    }

    @Test
    public void updateInfoUpdatesScoreOnPlayerLoss() {
        gameInfo.updateInfo(Symbol.PAPER, Symbol.SCISSORS, GameOutcome.PLAYER_LOSS);

        assertEquals(0, gameInfo.getPlayerWins());
        assertEquals(1, gameInfo.getComputerWins());
    }

    @Test
    public void scoreStaysTheSameAfterDraw() {
        gameInfo.updateInfo(Symbol.PAPER, Symbol.PAPER, GameOutcome.DRAW);

        assertEquals(0, gameInfo.getPlayerWins());
        assertEquals(0, gameInfo.getComputerWins());
    }

    @Test
    public void updateInfoUpdatesChoices() {
        gameInfo.updateInfo(Symbol.PAPER, Symbol.SCISSORS, GameOutcome.PLAYER_WIN);

        assertEquals(Symbol.SCISSORS, gameInfo.getComputerChoice());
        assertEquals(Symbol.PAPER, gameInfo.getPlayerChoice());
    }


}
