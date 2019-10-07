package ru.job4j.calculator;

/**
 * EngCalculator.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class EngCalculator extends Calculator {

    /**
     * Method sin.
     * @param first - first number.
     * @return result of calculation of a sine.
     */
    public double sin(double first) {
        return Math.sin(Math.toRadians(first));
    }

    /**
     * Method cos.
     * @param first - first number.
     * @return result of calculation of a cosine.
     */
    public double cos(double first) {
        return Math.cos(Math.toRadians(first));
    }

    /**
     * Method tg.
     * @param first - first number.
     * @return result of calculation of a tangent.
     */
    public double tg(double first) {
        return Math.tan(Math.toRadians(first));
    }
}