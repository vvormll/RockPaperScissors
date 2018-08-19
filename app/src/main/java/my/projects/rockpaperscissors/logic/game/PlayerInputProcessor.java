package my.projects.rockpaperscissors.logic.game;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class PlayerInputProcessor {

    private static final String quitString = "QUIT";

    public boolean isQuitInput(String input) {
        return input.equals(quitString);
    }

    public Symbol getSymbolFromPlayerInput(String input) throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("String must not be empty");
        }

        Symbol symbol = null;
        try {
            symbol = Symbol.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(buildUndefinedSymbolMessage());
        }

        return symbol;
    }

    private String buildUndefinedSymbolMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Symbol must be one of ");
        for (Symbol symbol : Symbol.values()) {
            builder.append(symbol.name());
            builder.append(" ");
        }
        return builder.toString();
    }

}
