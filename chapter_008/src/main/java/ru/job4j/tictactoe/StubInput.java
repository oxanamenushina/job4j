package ru.job4j.tictactoe;

import java.util.List;

/**
 * StubInput.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    /**
     * The list of user answers.
     */
    private List<String> answers;

    /**
     * The index of the user's answer list.
     */
    private int position = 0;

    /**
     * The CheckInput object.
     */
    private CheckInput ci;

    public StubInput(List<String> answers, CheckInput ci) {
        this.answers = answers;
        this.ci = ci;
    }

    /**
     * The method outputs the question and gets the answer.
     * @param question - question.
     * @param number - question index from the menu list.
     * @return the answer to the question.
     */
    @Override
    public String ask(String question, int number) {
        String answer = this.answers.get(this.position++);
        if (!this.ci.check(answer, number)) {
            throw new IncorrectDataException();
        }
        return answer;
    }
}