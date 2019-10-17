package ru.job4j.menu;

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
     * @return the answer to the question.
     */
    String ask(String question);
}
