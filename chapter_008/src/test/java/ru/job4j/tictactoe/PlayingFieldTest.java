package ru.job4j.tictactoe;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * PlayingFieldTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class PlayingFieldTest {

    @Test
    public void whenCreateThenFieldOfThreeCellsIsCreated() {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        field.create(3);
        Cell[][] expected = new Cell[3][3];
        IntStream.range(0, 3).forEach(i -> IntStream.range(0, 3)
                .forEach(j -> expected[i][j] = factory.getCell(new int[]{i, j})));
        assertThat(field.getField(), is(expected));
    }

    @Test
    public void whenCreateThenFieldOfFiveCellsIsCreated() {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        field.create(5);
        Cell[][] expected = new Cell[5][5];
        IntStream.range(0, 5).forEach(i -> IntStream.range(0, 5)
                .forEach(j -> expected[i][j] = factory.getCell(new int[]{i, j})));
        assertThat(field.getField(), is(expected));
    }

    @Test
    public void whenSetXInCellThenThisCellHasX() {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        field.create(3);
        field.set(1, 1, true);
        assertThat(field.getCell(1, 1).getSign(), is("X"));
    }

    @Test
    public void whenSet0InCellThenThisCellHas0() {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        field.create(3);
        field.set(2, 1, false);
        assertThat(field.getCell(2, 1).getSign(), is("0"));
    }

    @Test
    public void whenEmptyCellIsEmptyThenReturnTrue() {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        field.create(3);
        assertThat(field.isEmpty(1, 1), is(true));
    }

    @Test
    public void whenCellWithXIsEmptyThenReturnFalse() {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        field.create(3);
        field.set(1, 1, true);
        assertThat(field.isEmpty(1, 1), is(false));
    }

    @Test
    public void whenCellWith0IsEmptyThenReturnFalse() {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        field.create(3);
        field.set(2, 1, false);
        assertThat(field.isEmpty(2, 1), is(false));
    }
}