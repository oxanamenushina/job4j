package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.Moving;

/**
 * @version $Id$
 * @since 0.1
 */
public class KingBlack extends Figure {
    private final Cell position;

    public KingBlack(final Cell position) {
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
        if (!moving.isOneStep(source, dest)) {
            throw new ImpossibleMoveException();
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}
