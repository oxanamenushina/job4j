package ru.job4j.tictactoe;

/**
 * Cell.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Cell {

    /**
     * The method sets the sign in the cell.
     * @param isX true - the sign is X,
     * false - the sign is 0.
     */
    void setSign(boolean isX);

    /**
     * The method returns the coordinates of the cell.
     * @return the coordinates of the cell.
     */
    int[] getCoord();

    /**
     * The method returns the sign.
     * @return the sign.
     */
    String getSign();
}