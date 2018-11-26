package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    @Test
    public void whenPyramid4Height() {
        Paint paint = new Paint();
        String paintPyramid = paint.pyramid(4);
        assertThat(paintPyramid,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^   ")
                                .add("  ^^^  ")
                                .add(" ^^^^^ ")
                                .add("^^^^^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramid2Height() {
        Paint paint = new Paint();
        String paintPyramid = paint.pyramid(2);
        assertThat(paintPyramid,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add(" ^ ")
                                .add("^^^")
                                .toString()
                )
        );
    }
}
