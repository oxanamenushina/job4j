package ru.job4j.tictactoe;

/**
 * CellFactory.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface CellFactory {

    /**
     * The method creates an object of type Cell.
     * @param coord the coordinates of an object of type Cell.
     * @return an object of type Cell.
     */
    Cell getCell(int[] coord);
}