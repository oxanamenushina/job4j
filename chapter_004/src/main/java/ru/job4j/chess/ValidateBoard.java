package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;

/**
 * @version $Id$
 * @since 0.1
 */
public class ValidateBoard extends Board {

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            rst = super.move(source, dest);
        } catch (FigureNotFoundException fnfe) {
            System.out.print("There is no figure in this cell.");
            System.out.print(System.lineSeparator());
        } catch (ImpossibleMoveException ime) {
            System.out.print("This figure can't move this way.");
            System.out.print(System.lineSeparator());
        } catch (OccupiedWayException owe) {
            System.out.print("The figure's path is occupied.");
            System.out.print(System.lineSeparator());
        }
        return rst;
    }
}