package ru.job4j.calculator;

import java.util.List;

/**
 * Actions.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Actions {

    /**
     * The method fills the list with arithmetic operations.
     * @param action - operations.
     */
    void fillActions(Action... action);

    /**
     * The method returns the number of values
     * required for the arithmetic operation.
     * @param key - the name of the operation.
     * @return the number of values.
     */
    int getCountValuesByKey(String key);

    /**
     * The method returns the list with arithmetic operations.
     * @return the list with arithmetic operations.
     */
    List<Action> getActions();
}