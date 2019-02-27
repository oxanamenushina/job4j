package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {
    /**
     * Метод получает двумерный массив целых чисел и добавляет их в List<Integer>.
     * @param array двумерный массив
     * @return лист с элементами двумерного массива
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int cell : row) {
                list.add(cell);
            }
        }
        return list;
    }
}
