package ru.job4j.array;

/**
 * Square.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Square {
    /**
     * Метод заполнения массива квадратами чисел.
     *
     * @param bound размер массива.
     * @return массив, заполненный квардатами чисел от 1 до bound.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < rst.length; i++) {
            rst[i] = (i + 1) * (i + 1);
        }
        return rst;
    }
}