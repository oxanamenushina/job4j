package ru.job4j.shape;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(
                square.draw(),
                is(
                        new StringBuilder()
                                .append("*******")
                                .append(System.lineSeparator())
                                .append("*******")
                                .append(System.lineSeparator())
                                .append("*******")
                                .append(System.lineSeparator())
                                .append("*******")
                                .append(System.lineSeparator())
                                .append("*******")
                                .append(System.lineSeparator())
                                .append("*******")
                                .append(System.lineSeparator())
                                .append("*******")
                                .toString()
                )
        );
    }
}
