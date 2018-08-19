package my.projects.rockpaperscissors.strategy.picker;

import my.projects.rockpaperscissors.logic.CircularIteratorBuilder;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class CircularStrategyPicker implements StrategyPicker {

    private Iterator<SymbolPickingStrategy> circularIterator;

    public CircularStrategyPicker(Collection<SymbolPickingStrategy> strategies) {
        circularIterator = CircularIteratorBuilder.buildCircularIterator(Collections.unmodifiableCollection(strategies));
    }

    @Override
    public SymbolPickingStrategy pickNextStrategy(SymbolPickingStrategy currentStrategy) {
        return circularIterator.next();
    }

}
