package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class FactorialTest {
    /**
     * Test Проверка расчета факториала целого положительного числа, отличного от 0.
     */
    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        Factorial factorialNumbers = new Factorial();
        int result = factorialNumbers.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }

    /**
     * Test Проверка расчета факториала 0.
     */
    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial factorialNumbers = new Factorial();
        int result = factorialNumbers.calc(0);
        int expected = 1;
        assertThat(result, is(expected));
    }
}