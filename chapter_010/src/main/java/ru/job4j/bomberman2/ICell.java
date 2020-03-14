package ru.job4j.bomberman2;

/**
 * ICell.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface ICell {

    /**
     * The method returns the x-coordinate of the cell.
     * @return the x-coordinate of the cell.
     */
    int getX();

    /**
     * The method returns the y-coordinate of the cell.
     * @return the y-coordinate of the cell.
     */
    int getY();
}