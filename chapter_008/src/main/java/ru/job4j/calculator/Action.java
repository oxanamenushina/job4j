package ru.job4j.calculator;

/**
 * Action.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Action {

    /**
     * The method returns the key of the arithmetic operation.
     * @return the key of the arithmetic operation.
     */
    String key();

    /**
     * The method performs an arithmetic operation.
     * @param values - numbers.
     * @return the result of the arithmetic operation.
     */
    double execute(Double... values);

    /**
     * The method returns the number of values
     * required for the arithmetic operation.
     * @return the number of values.
     */
    int countValues();

    /**
     * The method returns information about the arithmetic operation.
     * @return information about the arithmetic operation.
     */
    String info();
}