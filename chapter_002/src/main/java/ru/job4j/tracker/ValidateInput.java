package ru.job4j.tracker;

import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Метод выводит на консоль вопрос, принимает значение с консоли
     * и проверяет входит ли значение в заданный диапазон.
     * @param question
     * @param range
     * @return ответ пользователя.
     */
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.print("Please select key from menu.");
                System.out.print(System.lineSeparator());
            } catch (NumberFormatException nfe) {
                System.out.print("Please enter validate data again.");
                System.out.print(System.lineSeparator());
            }
        } while (invalid);
        return value;
    }
}
