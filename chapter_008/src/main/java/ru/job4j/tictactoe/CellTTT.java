package ru.job4j.tictactoe;

import java.util.Arrays;

/**
 * CellTTT.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CellTTT implements Cell {

    /**
     * The sign of the cell.
     */
    private String sign = " ";
    /**
     * The coordinates of the cell.
     */
    private int[] coord;

    public CellTTT(int[] coord) {
        this.coord = coord;
    }

    /**
     * The method sets the sign in the cell.
     * @param isX true - the sign is X,
     * false - the sign is 0.
     */
    @Override
    public void setSign(boolean isX) {
        this.sign = isX ? "X" : "0";
    }

    /**
     * The method returns the coordinates of the cell.
     * @return the coordinates of the cell.
     */
    @Override
    public int[] getCoord() {
        return this.coord;
    }

    /**
     * The method returns the sign.
     * @return the sign.
     */
    @Override
    public String getSign() {
        return this.sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CellTTT cellTTT = (CellTTT) o;
        return Arrays.equals(coord, cellTTT.coord);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coord);
    }
}