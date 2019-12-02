package ru.job4j.tictactoe;

/**
 * CellTTTFactory.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CellTTTFactory implements CellFactory {

    /**
     * The method creates an object of type Cell.
     * @param coord the coordinates of an object of type Cell.
     * @return an object of type Cell.
     */
    @Override
    public Cell getCell(int[] coord) {
        return new CellTTT(coord);
    }
}