package ru.job4j.bomberman2;

/**
 * Input.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Input<T> {

    /**
     * The method returns user input.
     * @return user input.
     */
    T getValue();
}