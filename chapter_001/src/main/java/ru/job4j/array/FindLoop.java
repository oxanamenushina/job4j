package ru.job4j.array;

/**
 * FindLoop.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class FindLoop {
    /**
     * Метод поиска элемента в массиве.
     *
     * @param data массив.
     * @param el целое число, которое нужно найти в массиве.
     * @return индекс массива или -1.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
