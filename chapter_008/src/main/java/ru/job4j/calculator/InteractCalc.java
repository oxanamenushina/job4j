package ru.job4j.calculator;

import com.sun.javafx.runtime.async.AbstractRemoteResource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * InteractCalc.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class InteractCalc {

    private Input input;
    private Consumer<String> output;
    private Actions actions;
    private Map<String, VarFunction<Double, Double>> operations;
    private List<String> menu;
    private boolean isFirst = true;
    private double lastRes = 0;

    public InteractCalc(Input input, Consumer<String> output, Actions actions) {
        this.input = input;
        this.output = output;
        this.actions = actions;
        CalculatorMenu cm = new CalculatorMenu(this.actions);
        this.menu = cm.getMenu();
        this.operations = cm.getOperations();
    }

    /**
     * The method starts the main loop.
     */
    public void calculate() {
        boolean isCont;
        do {
            String operation = this.input.ask(this.menu.get(0), 0, this.isFirst, null, this.lastRes);
            isCont = !"exit".equalsIgnoreCase(operation);
            if (isCont) {
                this.action(operation);
            }
            this.isFirst = false;
        } while (isCont);
    }

    /**
     * The method performs a specified arithmetic operation
     * on the values entered by the user.
     * @param operation - the key of arithmetic operation.
     */
    private void action(String operation) {
        Double[] values = IntStream.range(0, this.actions.getCountValuesByKey(operation))
                .mapToDouble(n -> this.getValue(n + 1, operation))
                .boxed().toArray(Double[]::new);
        this.lastRes = this.operations.get(operation).apply(values);
        this.output.accept("Result: " + this.lastRes + System.lineSeparator());
    }

    /**
     * The method gets the value entered by the user.
     * @param number - question index from the menu list.
     * @param oper - the key of arithmetic operation.
     * @return the value entered by the user.
     */
    private double getValue(int number, String oper) {
        String value = this.isFirst
                ? this.input.ask(this.menu.get(number), number, this.isFirst, oper, this.lastRes)
                : this.input.ask(this.menu.get(number) + this.menu.get(3), number, this.isFirst, oper, this.lastRes);
        return "m".equalsIgnoreCase(value) && !this.isFirst ? this.lastRes : Double.parseDouble(value);
    }
}