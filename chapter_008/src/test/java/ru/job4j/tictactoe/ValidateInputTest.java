package ru.job4j.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * ValidateInputTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {

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
    public void whenSizeAllowedThenCorrectInput() {
        Field field = new PlayingField(new CellTTTFactory());
        Input input = new ValidateInput(new StubInput(List.of("99"), new CheckInput(field)));
        input.ask("Size", 2);
        assertThat(this.mem.toString(), is(""));
    }

    @Test
    public void whenWordEnteredThenInvalidInput() {
        Field field = new PlayingField(new CellTTTFactory());
        Input input = new ValidateInput(new StubInput(List.of("invalid", "3"), new CheckInput(field)));
        input.ask("Size", 2);
        assertThat(this.mem.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }

    @Test
    public void whenSizeLargerThanAllowedThenInvalidInput() {
        Field field = new PlayingField(new CellTTTFactory());
        Input input = new ValidateInput(new StubInput(List.of("150", "99"), new CheckInput(field)));
        input.ask("Size", 2);
        assertThat(this.mem.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }

    @Test
    public void whenSizeLessThanAllowedThenInvalidInput() {
        Field field = new PlayingField(new CellTTTFactory());
        Input input = new ValidateInput(new StubInput(List.of("2", "10"), new CheckInput(field)));
        input.ask("Size", 2);
        assertThat(this.mem.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }

    @Test
    public void whenCorrectSignEnteredThenCorrectInput() {
        Field field = new PlayingField(new CellTTTFactory());
        Input input = new ValidateInput(new StubInput(List.of("x"), new CheckInput(field)));
        input.ask("Sign", 3);
        assertThat(this.mem.toString(), is(""));
    }

    @Test
    public void whenIncorrectSignEnteredThenInvalidInput() {
        Field field = new PlayingField(new CellTTTFactory());
        Input input = new ValidateInput(new StubInput(List.of("S", "0"), new CheckInput(field)));
        input.ask("Sign", 3);
        assertThat(this.mem.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }

    @Test
    public void whenCorrectCoordinatesEnteredThenCorrectInput() {
        Field field = new PlayingField(new CellTTTFactory());
        field.create(3);
        Input input = new ValidateInput(new StubInput(List.of("1, 1"), new CheckInput(field)));
        input.ask("Coordinates", 4);
        assertThat(this.mem.toString(), is(""));
    }

    @Test
    public void whenIncorrectCoordinatesEnteredThenInvalidInput() {
        Field field = new PlayingField(new CellTTTFactory());
        field.create(3);
        Input input = new ValidateInput(new StubInput(List.of("10, 20", "2, 2"), new CheckInput(field)));
        input.ask("Coordinates", 4);
        assertThat(this.mem.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }

    @Test
    public void whenCoordinatesOfOccupiedCellEnteredThenInvalidInput() {
        Field field = new PlayingField(new CellTTTFactory());
        field.create(3);
        field.set(1, 1, true);
        Input input = new ValidateInput(new StubInput(List.of("2, 2", "1, 1"), new CheckInput(field)));
        input.ask("Coordinates", 4);
        assertThat(this.mem.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }
}