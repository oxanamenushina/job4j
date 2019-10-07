package ru.job4j.calculator;

/**
 * Input.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Input {

    /**
     * The method outputs the question and takes the value.
     * @param question - question.
     * @param number - question index from the menu list.
     * @param isFirst - a boolean value that indicates
     * whether the arithmetic operation is the first.
     * @param operation - the key of arithmetic operation.
     * @param lastRes - the result of the previous arithmetic operation.
     * @return the answer to the question.
     */
    String ask(String question, int number, boolean isFirst, String operation, double lastRes);
}
