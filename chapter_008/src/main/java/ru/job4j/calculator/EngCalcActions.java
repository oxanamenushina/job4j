package ru.job4j.calculator;

/**
 * EngCalcActions.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class EngCalcActions extends CalcActions {

    private EngCalculator calc;

    public EngCalcActions(EngCalculator calc, Actions actions) {
        this.calc = calc;
        this.fillActions(actions.getActions().toArray(new Action[0]));
        this.fillActions(new Sine(), new Cosine(), new Tangent());
    }

    /**
     * Sine.
     */
    public class Sine extends BaseAction {

        public Sine() {
            super("sin", "Sine");
        }

        @Override
        public double execute(Double... values) {
            return calc.sin(values[0]);
        }

        @Override
        public int countValues() {
            return 1;
        }
    }

    /**
     * Cosine.
     */
    public class Cosine extends BaseAction {

        public Cosine() {
            super("cos", "Cosine");
        }

        @Override
        public double execute(Double... values) {
            return calc.cos(values[0]);
        }

        @Override
        public int countValues() {
            return 1;
        }
    }

    /**
     * Tangent.
     */
    public class Tangent extends BaseAction {

        public Tangent() {
            super("tg", "Tangent");
        }

        @Override
        public double execute(Double... values) {
            return calc.tg(values[0]);
        }

        @Override
        public int countValues() {
            return 1;
        }
    }
}