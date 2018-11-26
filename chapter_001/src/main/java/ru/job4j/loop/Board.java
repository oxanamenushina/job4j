package ru.job4j.loop;

/**
 * Board.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Board {

    /**
     * Метод построения шахматной доски.
     *
     * @param width ширина доски.
     * @param height высота доски.
     * @return доска.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}