package ru.job4j.tictactoe;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * GameLogicTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class GameLogicTest {

    @Test
    public void whenFirstMoveThenReturnsTrue() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, false);
        assertThat(logic.move(1, 1), is(true));
    }

    @Test
    public void whenNoEmptyCellsThenMoveReturnsFalse() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, false);
        logic.move(1, 1);
        logic.move(2, 0);
        logic.move(0, 1);
        logic.move(1, 2);
        assertThat(logic.move(2, 2), is(false));
    }

    @Test
    public void whenThereAreFreeCellsThenIsFilledReturnsFalse() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, false);
        logic.move(1, 1);
        assertThat(logic.isFilled(), is(false));
    }

    @Test
    public void whenThereAreNoFreeCellsThenIsFilledReturnsTrue() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, false);
        logic.move(1, 1);
        logic.move(2, 0);
        logic.move(0, 1);
        logic.move(1, 2);
        logic.move(2, 2);
        assertThat(logic.isFilled(), is(true));
    }

    @Test
    public void whenComputerDidNotWinThenIsCompWinReturnsFalse() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, false);
        logic.move(1, 1);
        logic.move(2, 0);
        logic.move(0, 1);
        logic.move(1, 2);
        logic.move(2, 2);
        assertThat(logic.isCompWin(), is(false));
    }

    @Test
    public void whenComputerWonThenIsCompWinReturnsTrue() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, true);
        logic.move(2, 2);
        logic.move(0, 2);
        assertThat(logic.isCompWin(), is(true));
    }

    @Test
    public void whenUserDidNotWinThenIsUserWinReturnsFalse() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, false);
        logic.move(1, 1);
        logic.move(2, 0);
        logic.move(0, 1);
        logic.move(1, 2);
        logic.move(2, 2);
        assertThat(logic.isUserWin(), is(false));
    }

    @Test
    public void whenUserWonThenIsUserWinReturnsTrue() {
        Field field = new PlayingField(new CellTTTFactory());
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        logic.init(3, false);
        field.set(0, 0, false);
        field.set(1, 1, true);
        field.set(1, 2, false);
        field.set(2, 0, true);
        logic.move(0, 2);
        assertThat(logic.isUserWin(), is(true));
    }
}