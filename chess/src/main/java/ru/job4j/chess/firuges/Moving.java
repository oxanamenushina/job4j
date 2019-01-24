package ru.job4j.chess.firuges;

/**
 * @version $Id$
 * @since 0.1
 */
public class Moving {

    /**
     * Метод проверяет движение по горизонтали или вертикали.
     * @param source
     * @param dest
     * @return true - движение по горизонтали или вертикали и false - нет.
     */
    public boolean isStraight(Cell source, Cell dest) {
        boolean rst = false;
        if (((dest.x - source.x != 0) && (dest.y - source.y == 0))
                ^ ((dest.x - source.x == 0) && (dest.y - source.y != 0))) {
            rst = true;
        }
        return rst;
    }

    /**
     * Метод проверяет движение по диагонали.
     * @param source
     * @param dest
     * @return true - движение по диагонали и false - нет.
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        boolean diagonal = false;
        if (Math.abs(dest.x - source.x) == Math.abs(dest.y - source.y)) {
            diagonal = true;
        }
        return diagonal;
    }

    /**
     * Метод проверяет движение по вертикали вперед на один шаг или 2 шага на первом ходу.
     * @param source
     * @param dest
     * @return true - движение по вертикали вперед на один шаг или 2 шага на первом ходу и false - нет.
     */
    public boolean isVertical(Cell source, Cell dest) {
        boolean rst = false;
        if (((dest.y - source.y == 1) && (dest.x - source.x == 0))
                || ((source.y == 1) && (dest.y == 3) && (dest.x - source.x == 0))) {
            rst = true;
        }
        return rst;
    }

    /**
     * Метод создает массив ячеек пути фигуры по горизонтали или вертикали.
     * @param source
     * @param dest
     * @return массив ячеек пути фигуры.
     */
    public Cell[] straightWay(Cell source, Cell dest) {
        int count = dest.x - source.x != 0 ? Math.abs(dest.x - source.x) : Math.abs(dest.y - source.y);
        Cell[] steps = new Cell[count];
        Cell step = Cell.A1;
        if (dest.x - source.x != 0) {
            int indX = (dest.x - source.x) > 0 ? 1 : -1;
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.values()[(source.x + indX * (i + 1)) * 8 + source.y];
            }
        } else {
            int indY = (dest.y - source.y) > 0 ? 1 : -1;
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.values()[source.x * 8 + source.y + indY * (i + 1)];
            }
        }
        return steps;
    }

    /**
     * Метод создает массив ячеек пути фигуры по диагонали.
     * @param source
     * @param dest
     * @return массив ячеек пути фигуры.
     */
    public Cell[] diagonalWay(Cell source, Cell dest) {
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        int indX = (dest.x - source.x) > 0 ? 1 : -1;
        int indY = (dest.y - source.y) > 0 ? 1 : -1;
        for (int i = 0; i < steps.length; i++) {
            steps[i] = Cell.values()[(source.x + indX * (i + 1)) * 8 + source.y + indY * (i + 1)];
        }
        return steps;
    }

    /**
     * Метод проверяет движение буквой Г.
     * @param source
     * @param dest
     * @return true - движение буквой Г и false - нет.
     */
    public boolean isAngle(Cell source, Cell dest) {
        boolean angle = false;
        if (((Math.abs(dest.x - source.x) == 1) && (Math.abs(dest.y - source.y) == 2))
                ^ ((Math.abs(dest.x - source.x) == 2) && (Math.abs(dest.y - source.y) == 1))) {
            angle = true;
        }
        return angle;
    }

    /**
     * Метод проверяет движение на 1 шаг в любую сторону.
     * @param source
     * @param dest
     * @return true - движение движение на 1 шаг в любую сторону и false - нет.
     */
    public boolean isOneStep(Cell source, Cell dest) {
        boolean rst = false;
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        if ((Math.abs(deltaX) == 0 && Math.abs(deltaY) == 1) || (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 0)
                || (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 1)) {
            rst = true;
        }
        return rst;
    }
}
