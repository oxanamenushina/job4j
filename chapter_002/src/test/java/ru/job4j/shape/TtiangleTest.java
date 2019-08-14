package ru.job4j.shape;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class TtiangleTest {
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                        .append("   *   ")
                        .append(System.lineSeparator())
                        .append("  ***  ")
                        .append(System.lineSeparator())
                        .append(" ***** ")
                        .append(System.lineSeparator())
                        .append("*******")
                        .toString()
                )
        );
    }
}
