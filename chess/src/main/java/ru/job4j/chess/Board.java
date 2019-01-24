package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.white.KnightWhite;
import ru.job4j.chess.firuges.black.KnightBlack;

/**
 * @version $Id$
 * @since 0.1
 */
public class Board {

    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * Метод добавляет фигуру в массив фигур.
     * @param figure
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Метод перемещает фигуру на другую ячейку.
     * @param source
     * @param dest
     * @return true в случае, если фигура перемещениа на другую ячейку.
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean rst = false;

            int index = this.findBy(source);
            if (index == -1) {
                throw new FigureNotFoundException();
            }
            Cell[] steps = this.figures[index].way(source, dest);
            if (!(this.figures[index] instanceof KnightWhite) && !(this.figures[index] instanceof KnightBlack) && !isWayEmpty(steps)) {
                throw new OccupiedWayException();
            }
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        return rst;
    }

    /**
     * Метод удаляет из массива фигуры.
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Метод проверяет есть ли в ячейке фигура.
     * @param cell
     * @return ячейку массива в которой находится фигура или -1, если ячейка пуста.
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Метод проверяет свободен ли путь фигуры.
     * @param steps
     * @return true - путь свободен, false - путь занятц.
     */
    private boolean isWayEmpty(Cell[] steps) {
        boolean rst = true;
        for (int i = 0; i < steps.length; i++) {
            if (steps.length > 0 && this.findBy(steps[i]) != -1) {
                rst = false;
                break;
            }
        }
        return rst;
    }
}