package ru.job4j.chess.firuges;

import java.util.Arrays;

/**
 * @version $Id$
 * @since 0.1
 */
public class Moving {

    /**
     * Метод проверяет движение по горизонтали или вертикали.
     * @param source начальная ячейка.
     * @param dest конечная ячейка.
     * @return true - движение по горизонтали или вертикали и false - нет.
     */
    public boolean isStraight(Cell source, Cell dest) {
        return ((dest.x - source.x != 0) && (dest.y - source.y == 0))
                ^ ((dest.x - source.x == 0) && (dest.y - source.y != 0));
    }

    /**
     * Метод проверяет движение по диагонали.
     * @param source начальная ячейка.
     * @param dest конечная ячейка.
     * @return true - движение по диагонали и false - нет.
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(dest.x - source.x) == Math.abs(dest.y - source.y);
    }

    /**
     * Метод создает массив ячеек пути фигуры по горизонтали или вертикали.
     * @param source начальная ячейка.
     * @param dest конечная ячейка.
     * @return массив ячеек пути фигуры.
     */
    public Cell[] straightWay(Cell source, Cell dest) {
        return source.y - dest.y != 0 ? Arrays.stream(Cell.values()).filter(cell -> (cell.x == source.x)
                && (source.y < dest.y ? (cell.y > source.y && cell.y <= dest.y) : (cell.y < source.y && cell.y >= dest.y)))
                .sorted((o1, o2) -> source.y < dest.y ? Integer.compare(o1.y, o2.y) : Integer.compare(o2.y, o1.y))
                .toArray(Cell[]::new)
                : Arrays.stream(Cell.values()).filter(cell -> (cell.y == source.y)
                && (source.x < dest.x ? (cell.x > source.x && cell.x <= dest.x) : (cell.x < source.x && cell.x >= dest.x)))
                .sorted((o1, o2) -> source.x < dest.x ? Integer.compare(o1.x, o2.x) : Integer.compare(o2.x, o1.x))
                .toArray(Cell[]::new);
    }

    /**
     * Метод создает массив ячеек пути фигуры по диагонали.
     * @param source начальная ячейка.
     * @param dest конечная ячейка.
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
     * @param source начальная ячейка.
     * @param dest конечная ячейка.
     * @return true - движение буквой Г и false - нет.
     */
    public boolean isAngle(Cell source, Cell dest) {
        return ((Math.abs(dest.x - source.x) == 1) && (Math.abs(dest.y - source.y) == 2))
                ^ ((Math.abs(dest.x - source.x) == 2) && (Math.abs(dest.y - source.y) == 1));
    }

    /**
     * Метод проверяет движение на 1 шаг в любую сторону.
     * @param source начальная ячейка.
     * @param dest конечная ячейка.
     * @return true - движение движение на 1 шаг в любую сторону и false - нет.
     */
    public boolean isOneStep(Cell source, Cell dest) {
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        return (Math.abs(deltaX) == 0 && Math.abs(deltaY) == 1) || (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 0)
                || (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 1);
    }
}
