package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
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
        return Arrays.stream(array).flatMapToInt(Arrays::stream).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
