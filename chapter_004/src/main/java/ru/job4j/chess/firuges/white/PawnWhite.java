package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.ImpossibleMoveException;

import java.util.Arrays;

/**
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends Figure {
    private final Cell position;

    public PawnWhite(final Cell position) {
        super(position);
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!((dest.y - source.y == 1) && (dest.x - source.x == 0))
                ^ ((source.y == 1) && (dest.y == 3) && (dest.x - source.x == 0))) {
            throw new ImpossibleMoveException();
        }
        return Arrays.stream(Cell.values())
                .filter(cell -> (cell.x == source.x) && (cell.y > source.y && cell.y <= dest.y)).toArray(Cell[]::new);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
