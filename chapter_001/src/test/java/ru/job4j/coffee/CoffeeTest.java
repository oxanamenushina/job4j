package ru.job4j.coffee;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeTest {

    @Test
    public void when50And35Then15() {
        Coffee coffee = new Coffee();
        int[] result = coffee.changes(50, 35);
        int[] expect = {10, 5};
        assertThat(result, is(expect));
    }

    @Test
    public void when100And42Then58() {
        Coffee coffee = new Coffee();
        int[] result = coffee.changes(100, 42);
        int[] expect = {10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(result, is(expect));
    }

}
