package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack extends Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        super(position);
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!((source.y - dest.y == 1) && (dest.x - source.x == 0))
                || ((source.y == 6) && (dest.y == 4) && (dest.x - source.x == 0))) {
            throw new ImpossibleMoveException();
        }
        return Arrays.stream(Cell.values())
                .filter(cell -> (cell.x == source.x) && (cell.y < source.y && cell.y >= dest.y))
                .sorted((o1, o2) -> Integer.compare(o2.y, o1.y)).toArray(Cell[]::new);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
