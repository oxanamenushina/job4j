package ru.job4j.max;

/**
 * @author Oxana Menushina (oxsm@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Max {

    /**
     * Method max Поиск максимального из двух чисел.
     * @param first первое число.
     * @param second второе число.
     * @return Максимальное число.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}

