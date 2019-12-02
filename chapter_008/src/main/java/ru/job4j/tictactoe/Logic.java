package ru.job4j.tictactoe;

/**
 * Logic.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Logic {

    /**
     * Logic initialization.
     * @param size - field size.
     * @param isCompX true - the computer plays for X,
     * false - the computer plays for 0.
     */
    void init(int size, boolean isCompX);

    /**
     * The user and the computer make a move.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @return true - the user and the computer made a move,
     * false - the user or the computer didn't make a move.
     */
    boolean move(int x, int y);

    /**
     * The method checks if the field is completely filled.
     * @return true - the field is completely filled,
     * false - the field isn't completely filled.
     */
    boolean isFilled();

    /**
     * The method checks if the computer has won.
     * @return true - the computer has won,
     * false - the computer hasn't won.
     */
    boolean isCompWin();

    /**
     * The method checks if the user has won.
     * @return true - the user has won,
     * false - the user hasn't won.
     */
    boolean isUserWin();
}