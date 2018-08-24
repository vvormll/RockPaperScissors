package my.projects.rockpaperscissors.model.strategy.picker;

import my.projects.rockpaperscissors.model.util.CircularIteratorBuilder;
import my.projects.rockpaperscissors.model.strategy.SymbolPickingStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

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