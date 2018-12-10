package ru.job4j.array;
/**
 * ThreeArrays.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class ThreeArrays {
    /**
     * Метод создает отсортированный массив, состоящий из двух других отсортированных массива.
     * @param array1 Первый отсортированный массив.
     * @param array2 Второй отсортированный массив.
     * @return Отсортированный массив, состоящий из двух отсортированных масивов.
     */
    public int[] sortedArray(int[] array1, int[] array2) {
        int[] array3 = new int[array1.length + array2.length];
        int k = 0;
        int i = 0;
        int j = 0;
        int n = array1[0];
        int m = array2[0];
        while (k < array3.length) {
            if (n < m) {
                array3[k] = n;
                n = i < array1.length - 1 ? array1[++i] : array2[array2.length - 1];
            } else {
                array3[k] = m;
                m = j < array2.length - 1 ? array2[++j] : array1[array1.length - 1];
            }
            k++;
        }
        return array3;
    }
}
