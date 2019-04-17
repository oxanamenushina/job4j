package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

/**
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {

    final Cell position;

    /**
     * Конструктор.
     * @param position
     */
    public Figure(final Cell position) {
        this.position = position;
    }

    /**
     * Метод возвращает позицию фигуры.
     * @return явейка, на которой расположена фигура.
     */
    public abstract Cell position();

    /**
     * Метод пвозвращает массив ячеек пути фигуры.
     * @param source
     * @param dest
     * @return массив ячеек пути.
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }

    /**
     * Метод возвращает фигуру на новой позиции.
     * @param dest
     * @return фигура на новой позиции.
     */
    public abstract Figure copy(Cell dest);

}
