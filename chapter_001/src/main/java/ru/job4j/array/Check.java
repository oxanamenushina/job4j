package ru.job4j.array;
/**
 * FindLoop.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Check {
    /**
     * Метод, проверяющий являются ли все элементы массива только true или только false.
     *
     * @param data массив.
     * @return true - все элементы массива одинаковые, false - элементы массива разные.
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (boolean bool : data) {
            if (bool == data[0]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}
