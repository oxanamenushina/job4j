package ru.job4j.tictactoe;

/**
 * Display.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Display {

    /**
     * Display initialization.
     * @param size - field size.
     */
    void init(int size);

    /**
     * The method returns a graphical
     * representation of the playing field.
     * @return a graphical representation
     * of the playing field.
     */
    String display();

    /**
     * The method sets the specified sign
     * to the cell with the specified coordinates
     * in the graphical representation of the playing field.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @param isX true - X, false - 0.
     */
    void setSign(int x, int y, boolean isX);

    /**
     * The method clears the graphical
     * representation of the playing field.
     */
    void clear();
}