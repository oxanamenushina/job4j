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
            System.out.println("There is no figure in this cell.");
        } catch (ImpossibleMoveException ime) {
            System.out.println("This figure can't move this way.");
        } catch (OccupiedWayException owe) {
            System.out.println("The figure's path is occupied.");
        }
        return rst;
    }
}