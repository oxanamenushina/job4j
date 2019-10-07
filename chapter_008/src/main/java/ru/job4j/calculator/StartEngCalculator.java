package ru.job4j.calculator;

/**
 * StartEngCalculator.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StartEngCalculator {

    public static void main(String[] args) {
        EngCalculator calc = new EngCalculator();
        Actions actions = new EngCalcActions(calc, new InterCalcActions(new Calculator()));
        Input input = new ValidateInput(new ConsoleUI(new CheckInput(actions)));
        InteractCalc ic = new InteractCalc(input, System.out::println, actions);
        ic.calculate();
    }
}