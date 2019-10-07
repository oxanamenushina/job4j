package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * CalcActions.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public abstract class CalcActions implements Actions {

    private List<Action> actions = new ArrayList<>();

    @Override
    public void fillActions(Action... acts) {
        this.actions.addAll(List.of(acts));
    }

    @Override
    public int getCountValuesByKey(String key) {
        return this.actions.stream().filter(n -> n.key().equalsIgnoreCase(key)).findFirst().get().countValues();
    }

    @Override
    public List<Action> getActions() {
        return actions;
    }
}
