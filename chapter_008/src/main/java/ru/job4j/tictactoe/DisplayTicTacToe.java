package ru.job4j.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * DisplayTicTacToe.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DisplayTicTacToe implements Display {

    /**
     * A graphical representation of the playing field.
     */
    private List<String> field = new ArrayList<>();

    /**
     * Field size.
     */
    private int size;

    /**
     * Display initialization.
     * @param size - field size.
     */
    @Override
    public void init(int size) {
        this.size = size;
        this.createPlayingField();
    }

    /**
     * The method returns a graphical
     * representation of the playing field.
     * @return a graphical representation
     * of the playing field.
     */
    @Override
    public String display() {
        StringBuilder result = new StringBuilder();
        for (String str : this.field) {
            result.append(str);
        }
        return result.toString();
    }

    /**
     * The method sets the specified sign
     * to the cell with the specified coordinates
     * in the graphical representation of the playing field.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @param isX true - X, false - 0.
     */
    @Override
    public void setSign(int x, int y, boolean isX) {
        char sign = isX ? 'X' : '0';
        StringBuilder str = new StringBuilder(this.field.get(x));
        str.setCharAt((y - 1) * 4 + 5, sign);
        this.field.set(x, str.toString());
    }

    /**
     * The method clears the graphical
     * representation of the playing field.
     */
    @Override
    public void clear() {
        this.field.clear();
    }

    /**
     * The method creates a graphical
     * representation of the playing field.
     */
    private void createPlayingField() {
        StringBuilder first = new StringBuilder("    ");
        IntStream.range(1, this.size + 1).forEach(n -> first.append(String.format(" %s%s", n, n > 9 ? " " : "  ")));
        this.field.add(first.append(System.lineSeparator()).toString());
        for (int i = 0; i < this.size; i++) {
            StringBuilder str = new StringBuilder(i + 1 + (i >= 9 ? "     " : "      "));
            for (int j = 0; j < this.size - 1; j++) {
                str.append("|   ");
            }
            // "   "
            str.append(" ".repeat(i >= 9 ? 2 : 3)).append(System.lineSeparator());
            if (i < this.size - 1) {
                str.append("    ").append("-".repeat(this.size * 4 - 1));
            }
            this.field.add(str.append(System.lineSeparator()).toString());
        }
    }
}