package ru.job4j.tictactoe;

import java.util.stream.IntStream;

/**
 * PlayingField.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class PlayingField implements Field {

    /**
     * The array of cells.
     */
    private Cell[][] field;

    /**
     * The CellFactory object.
     */
    private CellFactory factory;

    public PlayingField(CellFactory factory) {
        this.factory = factory;
    }

    /**
     * The method creates a playing field.
     * @param size - field size.
     */
    @Override
    public void create(int size) {
        this.field = new Cell[size][size];
        IntStream.range(0, size).forEach(i -> IntStream.range(0, size)
                .forEach(j -> this.field[i][j] = this.factory.getCell(new int[]{i, j})));
    }

    /**
     * The method sets the specified sign
     * to the cell with the specified coordinates.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @param isX true - X, false - 0.
     */
    @Override
    public void set(int x, int y, boolean isX) {
        this.field[x][y].setSign(isX);
    }

    /**
     * The method returns the playing field.
     * @return the playing field.
     */
    @Override
    public Cell[][] getField() {
        return this.field;
    }

    /**
     * The method checks if the cell is empty.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @return true - the cell is empty,
     * false - the cell isn't empty.
     */
    @Override
    public boolean isEmpty(int x, int y) {
        return this.field[x][y].getSign().equals(" ");
    }

    /**
     * The method returns the cell
     * with the specified coordinates.
     * @param x - x-coordinate.
     * @param y - y-coordinate.
     * @return the cell.
     */
    @Override
    public Cell getCell(int x, int y) {
        return this.field[x][y];
    }
}