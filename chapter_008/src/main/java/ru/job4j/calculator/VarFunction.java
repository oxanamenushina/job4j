package ru.job4j.calculator;

/**
 * VarFunction.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface VarFunction<T, E> {

    E apply(T... value);
}