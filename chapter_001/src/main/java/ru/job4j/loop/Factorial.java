package ru.job4j.loop;

/**
 * Factorial.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Factorial {
    /**
     * Метод вычисления факториала.
     *
     * @param n положительное целое число.
     * @return факториал n.
     */
    public int calc(int n) {
        int factorial = 1;
        if (n > 0) {
            for (int index = 1; index <= n; index++) {
                factorial *= index;
            }
        }
        return factorial;
    }
}
