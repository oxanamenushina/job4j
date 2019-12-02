package ru.job4j.tictactoe;

/**
 * Field.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Field {

    /**
     * The method creates a playing field.
     * @param size - field size.
     */
    void create(int size);

    /**
     * The method sets the specified sign
     * to the cell with the specified coordinates.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @param isX true - X, false - 0.
     */
    void set(int x, int y, boolean isX);

    /**
     * The method returns the playing field.
     * @return the playing field.
     */
    Cell[][] getField();

    /**
     * The method checks if the cell is empty.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @return true - the cell is empty,
     * false - the cell isn't empty.
     */
    boolean isEmpty(int x, int y);

    /**
     * The method returns the cell
     * with the specified coordinates.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @return the cell.
     */
    Cell getCell(int x, int y);
}