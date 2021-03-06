package ru.job4j.function;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RangeFunctionTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        RangeFunction function = new RangeFunction();
        List<Double> result = function.diapason(7, 10, x -> 3 * x + 2);
        assertThat(result, is(List.of(23D, 26D, 29D)));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        RangeFunction function = new RangeFunction();
        List<Double> result = function.diapason(2, 5, x -> Math.pow(x, 2) + 1);
        assertThat(result, is(List.of(5D, 10D, 17D)));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        RangeFunction function = new RangeFunction();
        List<Double> result = function.diapason(4, 7, x -> Math.log(x * 100));
        assertThat(result, is(List.of(Math.log(400), Math.log(500), Math.log(600))));
    }
}
