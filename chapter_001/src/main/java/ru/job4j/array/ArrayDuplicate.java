package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplicate.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class ArrayDuplicate {
    /**
     * Метод, удаляющий повторяющиеся строки из массива строк.
     * @param array исходный массив.
     * @return массив с удаленными повторяющимися строками.
     */
    public String[] remove(String[] array) {
        int numberCopies = 0;
        for (int i = 0; i < array.length - numberCopies; i++) {
            for (int j = array.length - 1 - numberCopies; j >= 0; j--) {
                if (j != i && array[i].equals(array[j])) {
                    array[j] = array[array.length - 1 - numberCopies];
                    numberCopies++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - numberCopies);
    }
}
