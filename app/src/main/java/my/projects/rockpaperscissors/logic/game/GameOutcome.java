package my.projects.rockpaperscissors.logic.game;

public enum GameOutcome {
    PLAYER_WIN {
        @Override
        public String toString() {
            return "PLAYER WON";
        }
    }, PLAYER_LOSS {
        @Override
        public String toString() {
            return "COMPUTER WON";
        }
    }, DRAW {
        @Override
        public String toString() {
            return "DRAW";
        }
    };
}
