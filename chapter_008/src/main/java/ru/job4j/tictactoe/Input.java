package ru.job4j.tictactoe;

/**
 * Input.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Input {

    /**
     * The method outputs the question and gets the answer.
     * @param question - question.
     * @param number - question index from the menu list.
     * @return the answer to the question.
     */
    String ask(String question, int number);
}