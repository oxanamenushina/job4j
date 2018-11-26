package ru.job4j.loop;

/**
 * Counter.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Counter {
    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * @param start начальное число.
     * @param finish конечное число.
     * @return сумма четных чисел заданного диапазона.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int index = start; index <= finish; index++) {
            if (index % 2 == 0) {
                sum += index;
            }
        }
        return sum;
    }
}
