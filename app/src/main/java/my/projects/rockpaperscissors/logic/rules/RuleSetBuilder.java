package my.projects.rockpaperscissors.logic.rules;

import my.projects.rockpaperscissors.logic.symbol.Symbol;

public class RuleSetBuilder {

    public static RuleSet buildRockPaperScissorsRuleSet() {
        CircularRuleSet ruleSet = new CircularRuleSet();
        ruleSet.addSymbolToEnd(Symbol.ROCK);
        ruleSet.addSymbolToEnd(Symbol.PAPER);
        ruleSet.addSymbolToEnd(Symbol.SCISSORS);

        return ruleSet;
    }

}
