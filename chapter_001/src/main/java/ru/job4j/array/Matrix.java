package ru.job4j.array;

/**
 * Matrix.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Matrix {
    /**
     * Метод создания таблицы умножения.
     * @param size размер массивов.
     * @return массив элементов таблицы умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
