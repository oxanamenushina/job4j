package ru.job4j.calculator;

/**
 * Calculator.
 *
 * @author Oxana Menushina (oxsm@mail.ru)
 */
public class Calculator {

    /**
     * Method add.
     * @param first - first number.
     * @param second - second number.
     * @return result of addition.
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Method subtract.
     * @param first - first number.
     * @param second - second number.
     * @return result of subtraction.
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    /**
     * Method div.
     * @param first - first number.
     * @param second - second number.
     * @return result of division.
     */
    public double div(double first, double second) {
        return first / second;
    }

    /**
     * Method multiple.
     * @param first - first number.
     * @param second - second number.
     * @return result of multiplication.
     */
    public double multiple(double first, double second) {
        return first * second;
    }
}