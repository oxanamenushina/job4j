package ru.job4j.array;

import java.util.stream.IntStream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ArrayOfNumbers {
    /**
     * Метод отфильтровывает массив, оставляя только четные числа,
     * каждое число возводит в квадрат и суммирует все элементы.
     * @param array массив.
     * @return сумма чисел отфильтрованного массива, содержащего только четные числа, возведенные в квадрат.
     */
    public int operations(int[] array) {
        return IntStream.of(array)
                .filter(n -> n % 2 == 0)
                .map(n -> (int) Math.pow(n, 2.0))
                .reduce((n1, n2) -> n1 + n2)
                .orElse(0);
    }
}
