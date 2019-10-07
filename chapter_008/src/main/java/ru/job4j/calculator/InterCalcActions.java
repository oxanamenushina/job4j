package ru.job4j.calculator;

/**
 * InterCalcActions.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class InterCalcActions extends CalcActions {

    private Calculator calc;

    public InterCalcActions(Calculator calc) {
        this.calc = calc;
        fillActions(new Addition(), new Subtraction(), new Multiplication(), new Division());
    }

    /**
     * Addition.
     */
    public class Addition extends BaseAction {

        public Addition() {
            super("+", "addition");
        }

        @Override
        public double execute(Double... values) {
            return calc.add(values[0], values[1]);
        }

        @Override
        public int countValues() {
            return 2;
        }
    }

    /**
     * Subtraction.
     */
    public class Subtraction extends BaseAction {

        public Subtraction() {
            super("-", "subtraction");
        }

        @Override
        public double execute(Double... values) {
            return calc.subtract(values[0], values[1]);
        }

        @Override
        public int countValues() {
            return 2;
        }
    }

    /**
     * Multiplication.
     */
    public class Multiplication extends BaseAction {

        public Multiplication() {
            super("*", "multiplication");
        }

        @Override
        public double execute(Double... values) {
            return calc.multiple(values[0], values[1]);
        }

        @Override
        public int countValues() {
            return 2;
        }
    }

    /**
     * Division.
     */
    public class Division extends BaseAction {

        public Division() {
            super("/", "division");
        }

        @Override
        public double execute(Double... values) {
            return calc.div(values[0], values[1]);
        }

        @Override
        public int countValues() {
            return 2;
        }
    }
}