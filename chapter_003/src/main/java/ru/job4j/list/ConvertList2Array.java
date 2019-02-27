package ru.job4j.list;

import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {
    /**
     * Метод равномерно разбивает лист на количество строк двумерного массива.
     * Если количество элементов не кратно количеству строк, то оставшиеся значения в массиве заполняются нулями.
     * @param list лист
     * @param rows количество строк
     * @return двумерный масив с заданным количеством строк, заполненный элементами листа
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = 0;
        if (rows != 0) {
            cells = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        }
        int[][] array = new int[rows][cells];
        int i = 0;
        int k = 0;
            for (Integer num : list) {
                array[k][i] = num;
                if (i < cells - 1) {
                    i++;
                } else {
                    i = 0;
                    k++;
                }
            }
        return array;
    }
}

