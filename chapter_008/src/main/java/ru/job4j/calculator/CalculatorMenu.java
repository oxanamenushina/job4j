package ru.job4j.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CalculatorMenu.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CalculatorMenu {

    private List<String> questions = new ArrayList<>();
    private Map<String, VarFunction<Double, Double>> operations = new HashMap<>();
    private final List<Action> acts;

    public CalculatorMenu(Actions actions) {
        this.acts = actions.getActions();
    }

    /**
     * The method returns the menu of arithmetic operations.
     * @return the menu of arithmetic operations.
     */
    public List<String> getMenu() {
        this.fillMenu();
        return this.questions;
    }

    /**
     * The method creates the initial part of the menu.
     * @return the initial part of the menu.
     */
    private String beginning() {
        StringBuilder sb = new StringBuilder();
        sb.append("Enter the arithmetic operation:").append(System.lineSeparator());
        for (Action act : this.acts) {
            sb.append(act.info()).append(System.lineSeparator());
        }
        sb.append("enter \"exit\" to exit the calculator.").append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * The method fills the menu.
     */
    private void fillMenu() {
        this.questions.add(this.beginning());
        this.questions.add("Enter the number" + System.lineSeparator());
        this.questions.add("Enter the next number" + System.lineSeparator());
        this.questions.add("or enter  \"m\"   to use the result of the last calculation" + System.lineSeparator());
    }

    /**
     * The method fills Map with arithmetic operations.
     */
    private void fillOperations() {
        for (Action act : this.acts) {
            this.operations.put(act.key(), act::execute);
        }
    }

    /**
     * The method returns Map with arithmetic operations.
     * @return Map with arithmetic operations.
     */
    public Map<String, VarFunction<Double, Double>> getOperations() {
        this.fillOperations();
        return this.operations;
    }
}