package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ArrayOfNumbersTest {
    @Test
    public void whenArrayOfThreeNumbersThenSum4() {
        ArrayOfNumbers numbers = new ArrayOfNumbers();
        assertThat(numbers.operations(new int[]{1, 2, 3}), is(4));
    }

    @Test
    public void whenArrayOfFiveNumbersThenSum20() {
        ArrayOfNumbers numbers = new ArrayOfNumbers();
        assertThat(numbers.operations(new int[]{1, 2, 3, 4, 5}), is(20));
    }

    @Test
    public void whenArrayOfSixNumbersThenSum56() {
        ArrayOfNumbers numbers = new ArrayOfNumbers();
        assertThat(numbers.operations(new int[]{1, 2, 3, 4, 5, 6}), is(56));
    }
}
