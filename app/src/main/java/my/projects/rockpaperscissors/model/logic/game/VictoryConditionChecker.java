package my.projects.rockpaperscissors.model.logic.game;

import my.projects.rockpaperscissors.model.logic.rules.RuleSet;
import my.projects.rockpaperscissors.model.logic.rules.SymbolComparisonResult;
import my.projects.rockpaperscissors.model.logic.symbol.Symbol;

public class VictoryConditionChecker {

    private RuleSet ruleSet;

    public VictoryConditionChecker(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public GameOutcome checkOutcome(Symbol playerChoice, Symbol computerChoice) {
        SymbolComparisonResult comparisonResult = ruleSet.compareSymbols(playerChoice, computerChoice);

        switch (comparisonResult) {
            case EQUAL:
                return GameOutcome.DRAW;
            case BIGGER:
                return GameOutcome.PLAYER_WIN;
            case SMALLER:
                return GameOutcome.PLAYER_LOSS;
            case UNDEFINED:
            default:
                throw new IllegalStateException("Can't compare symbols: combination is not defined in the rule set (" + playerChoice.name() + ", " + computerChoice.name() + ")");
        }
    }
}
