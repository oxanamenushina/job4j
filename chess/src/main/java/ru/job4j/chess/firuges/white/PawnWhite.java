package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Moving;

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
        Moving moving = new Moving();
        if (!moving.isVertical(source, dest)) {
            throw new ImpossibleMoveException();
        }
        Cell[] steps = new Cell[dest.y - source.y];
        for (int i = 0; i < steps.length; i++) {
            for (Cell cell : Cell.values()) {
                if (cell.x == source.x && cell.y == source.y + i + 1) {
                    steps[i] = cell;
                    break;
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
