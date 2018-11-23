package ru.job4j.calculator;

/**
 * Calculate.
 *
 * @author Oxana Menushina (oxsm@mail.ru)
 */
public class Calculator {
    private double result;

    /**
     * Method add.
     * @param first - first number.
     * @param second - second number.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract.
     * @param first - first number.
     * @param second - second number.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method div.
     * @param first - first number.
     * @param second - second number.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method multiple.
     * @param first - first number.
     * @param second - second number.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method multiple.
     * @return result.
     */
    public double getResult() {
        return this.result;
    }
}