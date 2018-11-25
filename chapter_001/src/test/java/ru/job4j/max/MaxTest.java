package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {
    /**
     * Test Выбор максимального числа.
     */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 2);
        assertThat(result, is(3));
    }

}
