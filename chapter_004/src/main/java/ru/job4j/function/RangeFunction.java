package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RangeFunction {
    /**
     * Метод подсчета функции в диапазоне.
     * @param start начальное значение диапазона
     * @param end конечное значение диапазона
     * @param func функция
     * @return список значений подсчета функции в диапазоне
     */
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> values = new ArrayList<>();
        for (int index = start; index < end; index++) {
            values.add(func.apply((double) index));
        }
        return values;
    }
}
