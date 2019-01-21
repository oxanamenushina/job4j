package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.*;

/**
 * Board test.
 */
public class BoardTest {


    @Test
    public void whenKnightMoveFromB1ToC3ThenTrue() {
        Board board = new Board();
        board.add(new KnightWhite(Cell.B1));
        board.add(new PawnWhite(Cell.B2));
        assertThat(board.move(Cell.B1, Cell.C3), is(true));
    }

    @Test
    public void whenKnightMoveFromB1ToC3ThenFalse() {
        Board board = new Board();
        board.add(new PawnWhite(Cell.D2));
        assertThat(board.move(Cell.B1, Cell.C3), is(false));
    }

    @Test
    public void whenKnightMoveFromB1ToC6ThenFalse() {
        Board board = new Board();
        board.add(new KnightWhite(Cell.B1));
        assertThat(board.move(Cell.B1, Cell.C6), is(false));
    }

    @Test
    public void whenRookMoveFromA1ToA5ThenTrue() {
        Board board = new Board();
        board.add(new RookWhite(Cell.A1));
        assertThat(board.move(Cell.A1, Cell.A5), is(true));
    }

    @Test
    public void whenRookMoveFromA1ToE1ThenTrue() {
        Board board = new Board();
        board.add(new RookWhite(Cell.A1));
        assertThat(board.move(Cell.A1, Cell.E1), is(true));
    }

    @Test
    public void whenRookMoveFromA1ToD3ThenFalse() {
        Board board = new Board();
        board.add(new RookWhite(Cell.A1));
        assertThat(board.move(Cell.A1, Cell.D3), is(false));
    }

    @Test
    public void whenRookMoveFromA1ToA7ThenFalse() {
        Board board = new Board();
        board.add(new RookWhite(Cell.A1));
        board.add(new PawnWhite(Cell.A5));
        assertThat(board.move(Cell.A1, Cell.A7), is(false));
    }

    @Test
    public void whenBishopMoveFromC1ToA3ThenTrue() {
        Board board = new Board();
        board.add(new BishopWhite(Cell.C1));
        assertThat(board.move(Cell.C1, Cell.A3), is(true));
    }

    @Test
    public void whenBishopMoveFromC1ToE3ThenFalse() {
        Board board = new Board();
        board.add(new BishopWhite(Cell.C1));
        board.add(new PawnWhite(Cell.D2));
        assertThat(board.move(Cell.C1, Cell.E3), is(false));
    }

    @Test
    public void whenBishopMoveFromC1ToC4ThenFalse() {
        Board board = new Board();
        board.add(new BishopWhite(Cell.C1));
        assertThat(board.move(Cell.C1, Cell.C4), is(false));
    }

    @Test
    public void whenQueenMoveFromD1ToD7ThenFalse() {
        Board board = new Board();
        board.add(new QeenWhite(Cell.D1));
        board.add(new PawnWhite(Cell.D5));
        assertThat(board.move(Cell.D1, Cell.D7), is(false));
    }

    @Test
    public void whenQueenMoveFromD1ToD5ThenTrue() {
        Board board = new Board();
        board.add(new QeenWhite(Cell.D1));
        assertThat(board.move(Cell.D1, Cell.D5), is(true));
    }

    @Test
    public void whenQueenMoveFromD1ToA1ThenTrue() {
        Board board = new Board();
        board.add(new QeenWhite(Cell.D1));
        assertThat(board.move(Cell.D1, Cell.A1), is(true));
    }

    @Test
    public void whenQueenMoveFromD1ToG4ThenTrue() {
        Board board = new Board();
        board.add(new QeenWhite(Cell.D1));
        assertThat(board.move(Cell.D1, Cell.G4), is(true));
    }

    @Test
    public void whenQueenMoveFromD1ToF5ThenFalse() {
        Board board = new Board();
        board.add(new QeenWhite(Cell.D1));
        assertThat(board.move(Cell.D1, Cell.F5), is(false));
    }

    @Test
    public void whenKingMoveFromE1ToE2ThenTrue() {
        Board board = new Board();
        board.add(new KingWhite(Cell.E1));
        assertThat(board.move(Cell.E1, Cell.E2), is(true));
    }

    @Test
    public void whenKingMoveFromE1ToF1ThenTrue() {
        Board board = new Board();
        board.add(new KingWhite(Cell.E1));
        assertThat(board.move(Cell.E1, Cell.F1), is(true));
    }

    @Test
    public void whenKingMoveFromE1ToF2ThenTrue() {
        Board board = new Board();
        board.add(new KingWhite(Cell.E1));
        assertThat(board.move(Cell.E1, Cell.F2), is(true));
    }

    @Test
    public void whenKingMoveFromE1ToG3ThenFalse() {
        Board board = new Board();
        board.add(new KingWhite(Cell.E1));
        assertThat(board.move(Cell.E1, Cell.G3), is(false));
    }

    @Test
    public void whenKingMoveFromE1ToE7ThenFalse() {
        Board board = new Board();
        board.add(new KingWhite(Cell.E1));
        assertThat(board.move(Cell.E1, Cell.E7), is(false));
    }

    @Test
    public void whenPawnMoveFromD2ToD4ThenTrue() {
        Board board = new Board();
        board.add(new PawnWhite(Cell.D2));
        assertThat(board.move(Cell.D2, Cell.D4), is(true));
    }

    @Test
    public void whenPawnMoveFromD2ToD3ThenTrue() {
        Board board = new Board();
        board.add(new PawnWhite(Cell.D2));
        assertThat(board.move(Cell.D2, Cell.D3), is(true));
    }

    @Test
    public void whenPawnMoveFromD2ToE2ThenFalse() {
        Board board = new Board();
        board.add(new PawnWhite(Cell.D2));
        assertThat(board.move(Cell.D2, Cell.E2), is(false));
    }

    @Test
    public void whenPawnMoveFromD2ToE3ThenFalse() {
        Board board = new Board();
        board.add(new PawnWhite(Cell.D2));
        assertThat(board.move(Cell.D2, Cell.E3), is(false));
    }

}
