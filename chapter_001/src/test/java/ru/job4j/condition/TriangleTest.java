package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class TriangleTest {
    /**
     * Test Проверка расчета площади, если треугольник существует.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Test Проверка расчета площади, если треугольника не существует.
     */
    @Test
    public void whenTriangleNotExistThenMinus1() {
        Point a = new Point(0, 0);
        Point b = new Point(-3, 0);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = -1D;
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Test Проверка расчета полупериметра.
     */
    @Test
    public void periodTest() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 3);
        Point c = new Point(4, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.period(a.distanceTo(b), a.distanceTo(c), b.distanceTo(c));
        double expected = 6D;
        assertThat(result, closeTo(expected, 0.1));
    }
}