package ru.job4j.loop;

/**
 * Paint.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Paint {

    /**
     * Метод построения пирамиды из символов.
     *
     * @param height высота пирамиды.
     * @return пирамида из символов.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
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
