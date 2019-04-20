package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.PawnWhite;
import ru.job4j.chess.firuges.white.RookWhite;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @version $Id$
 * @since 0.1
 */
public class ValidateBoardTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenFigureNotFound() {
        ValidateBoard board = new ValidateBoard();
        board.add(new RookWhite(Cell.A1));
        board.move(Cell.C3, Cell.C7);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("There is no figure in this cell.%n")
                )
        );
    }

    @Test
    public void whenImpossibleWay() {
        ValidateBoard board = new ValidateBoard();
        board.add(new RookWhite(Cell.A1));
        board.move(Cell.A1, Cell.D4);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("This figure can't move this way.%n")
                )
        );
    }

    @Test
    public void whenWayOccupied() {
        ValidateBoard board = new ValidateBoard();
        board.add(new RookWhite(Cell.A1));
        board.add(new PawnWhite(Cell.A2));
        board.move(Cell.A1, Cell.A4);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("The figure's path is occupied.%n")
                )
        );
    }

}