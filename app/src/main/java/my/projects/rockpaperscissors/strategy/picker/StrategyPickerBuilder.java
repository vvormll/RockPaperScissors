package my.projects.rockpaperscissors.strategy.picker;

import my.projects.rockpaperscissors.strategy.RandomSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SimpleAdaptiveSymbolPickingStrategy;
import my.projects.rockpaperscissors.strategy.SymbolPickingStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyPickerBuilder {

    public static StrategyPicker buildDefaultCircularStrategyPicker() {
        List<SymbolPickingStrategy> list = new ArrayList<>();
        list.add(new RandomSymbolPickingStrategy());
        list.add(new SimpleAdaptiveSymbolPickingStrategy());

        return new CircularStrategyPicker(list);
    }
}
