package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Paint.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Paint {
    /**
     * Метод построения правой стороны пирамиды.
     *
     * @param height высота пирамиды.
     * @return правую часть пирамиды из символов.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Метод построения левой стороны пирамиды.
     *
     * @param height высота пирамиды.
     * @return левую часть пирамиды из символов.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Метод построения пирамиды из символов.
     *
     * @param height высота пирамиды.
     * @return пирамида из символов.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Метод установки символов в зависимости от условия.
     *
     * @param height высота.
     * @param weight ширина.
     * @param predict условие установки символов.
     * @return строки установленных символов.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}