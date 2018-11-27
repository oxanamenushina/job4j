package ru.job4j.array;

/**
 * MatrixCheck.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class MatrixCheck {
    /**
     * Метод, проверяющий раветство всех элементов диагоналей true или false.
     * @param data двумерный массив.
     * @return true - элементы на диагоналях одинаковые, false - элементы на диагоналях разные.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][i] != data[0][0] || data[i][data.length - i - 1] != data[0][data.length - 1]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
