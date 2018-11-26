package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class CounterTest {
    /**
     * Test Проверка расчета суммы четных чисел заданного диапазона.
     */
    @Test
    public void whenAddEvenFromOneToTenThenThirty() {
        Counter counterEven = new Counter();
        int result = counterEven.add(1, 10);
        int expected = 30;
        assertThat(result, is(expected));
    }
}
