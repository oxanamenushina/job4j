package ru.job4j.bomberman2;

/**
 * Field.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Field {

    /**
     * The method tries to move the hero from one cell to another.
     * @param source the source cell.
     * @param target the source cell.
     * @return true - the hero successfully moved from one cell to another,
     * false - the hero didn't move from one cell to another.
     */
    boolean move(ICell source, ICell target);

    /**
     * The method locks start cell.
     * @param cell the start cell.
     * @return true - the cell is locked,
     * false - the cell isn't locked.
     */
    boolean lockStart(ICell cell);

    /**
     * The method returns the board length.
     * @return the board length.
     */
    int getLength();

    /**
     * The method returns the board height.
     * @return the board height.
     */
    int getHeight();
}