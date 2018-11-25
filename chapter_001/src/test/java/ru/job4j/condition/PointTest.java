package ru.job4j.condition;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    /**
     * Test Вычисление расстояния между точками.
     */
    @Test
    public void calculationOfTheDistanceTest() {
        Point firstPoint = new Point(0, 1);
        double distance = firstPoint.distanceTo(new Point(2, 5));
        double expected = Math.sqrt(20);
        assertThat(distance, is(expected));
    }

    /**
     * Test Вывод на консоль координат точек и расстояния между ними.
     */
    @Test
    public void outputOfCoordinatesAndDistanceTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Point.main(null);
        String outputStrings = String.format("%s%n%s%n%s%n%s%n%s%f%n", "x1 = 0", "y1 = 1", "x2 = 2", "y2 = 5",
                "Расстояние между точками А и В : ", Math.sqrt(20));
        assertThat(out.toString(), is(outputStrings));
    }
}
