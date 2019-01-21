package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Moving;

/**
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite extends Figure {
    private final Cell position;

    public BishopWhite(final Cell position) {
        super(position);
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Moving moving = new Moving();
        if (!moving.isDiagonal(source, dest)) {
            throw new ImpossibleMoveException();
        }
        return moving.diagonalWay(source, dest);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
