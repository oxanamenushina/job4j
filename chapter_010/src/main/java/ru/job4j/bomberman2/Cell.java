package ru.job4j.bomberman2;

import java.util.Objects;

/**
 * Cell.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Cell implements ICell {

    /**
     * The x-coordinate of the cell.
     */
    private final int x;

    /**
     * The y-coordinate of the cell.
     */
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method returns the x-coordinate of the cell.
     * @return the x-coordinate of the cell.
     */
    public int getX() {
        return x;
    }

    /**
     * The method returns the y-coordinate of the cell.
     * @return the y-coordinate of the cell.
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}