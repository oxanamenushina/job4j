package ru.job4j.calculator;

/**
 * CheckInput.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CheckInput {

    private Actions actions;

    public CheckInput(Actions actions) {
        this.actions = actions;
    }

    /**
     * The method checks if the user's response is correct.
     * @param answer - the user's answer.
     * @param number - question index from the menu list.
     * @param isFirst - a boolean value that indicates
     * whether the arithmetic operation is the first.
     * @param operation - the key of arithmetic operation.
     * @param lastRes - the result of the previous arithmetic operation.
     * @return true - the user's response is correct,
     * false - the user's response isn't correct.
     */
    public boolean checkAnswer(String answer, int number, boolean isFirst, String operation, double lastRes) {
        return (number == 0 && this.isOperationOrExit(answer))
                || (number == 1 && this.checkNumbers(answer, isFirst))
                || (number == 2 && this.checkNumbers(answer, isFirst)
                && this.isNotDivisionByZero(answer, operation, lastRes));
    }

    /**
     * The method checks for division by zero.
     * @param answer - the user's answer.
     * @param operation - the key of arithmetic operation.
     * @param lastRes - the result of the previous arithmetic operation.
     * @return true - there is no division by zero,
     * false - there is division by zero.
     */
    private boolean isNotDivisionByZero(String answer, String operation, double lastRes) {
        return !operation.equals("/") || (answer.equals("m") ? lastRes != 0 : Double.parseDouble(answer) != 0);
    }

    /**
     * The method checks if the value is a number.
     * @param answer - the user's answer.
     * @param isFirst - a boolean value that indicates
     * whether the arithmetic operation is the first.
     * @return true - the value is a number,
     * false - the value isn't a number.
     */
    private boolean checkNumbers(String answer, boolean isFirst) {
        return isFirst ? this.isNumber(answer) : this.isNumber(answer) || answer.equals("m");
    }

    /**
     * The method checks if the entered by the user value is a number.
     * @param answer - the user's answer.
     * @return true - the entered by the user value is a number,
     * false - the entered by the user value isn't a number.
     */
    private boolean isNumber(String answer) {
        return answer.matches("-?\\d+([.,]?\\d+)?");
    }

    /**
     * The method checks whether the entered value
     * is an arithmetic operation key or an exit key.
     * @param answer - the user's answer.
     * @return true - the entered value
     * is an arithmetic operation key or an exit key,
     * false - the entered value isn't
     * an arithmetic operation key or an exit key.
     */
    private boolean isOperationOrExit(String answer) {
        return "exit".equalsIgnoreCase(answer)
                || this.actions.getActions().stream().anyMatch(n -> n.key().equalsIgnoreCase(answer));
    }
}