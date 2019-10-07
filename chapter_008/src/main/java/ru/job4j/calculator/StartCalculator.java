package ru.job4j.calculator;

/**
 * StartCalculator.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StartCalculator {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        InterCalcActions actions = new InterCalcActions(calc);
        Input input = new ValidateInput(new ConsoleUI(new CheckInput(actions)));
        InteractCalc ic = new InteractCalc(input, System.out::println, actions);
        ic.calculate();
    }
}