package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
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
        int cells = rows > 0 ? list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1 : 0;
        List<int[]> total = new ArrayList<>();
        for (int ind = 0; ind < rows; ind++) {
            total.add(Stream.concat(list.stream(), Arrays.stream(new int[rows * cells - list.size()]).boxed())
                    .mapToInt(val -> val).skip(ind * cells).limit(cells).toArray());
        }
        return total.toArray(new int[rows][cells]);
    }

    /**
     * Метод должен пройтись по всем элементам всех массивов в списке list и добавить их в один общий лист Integer.
     * @param list лист
     * @return лист Integer
     */
    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(Arrays::stream).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}

