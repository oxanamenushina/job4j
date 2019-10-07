package ru.job4j.calculator;

import java.util.List;

/**
 * StubInput.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    private List<String> answers;
    private int position = 0;
    private CheckInput ci;

    public StubInput(List<String> questions, CheckInput ci) {
        this.answers = questions;
        this.ci = ci;
    }

    @Override
    public String ask(String question, int number, boolean isFirst, String operation, double lastRes) {
        String answer = this.answers.get(this.position++);
        if (!this.ci.checkAnswer(answer, number, isFirst, operation, lastRes)) {
            throw new IncorrectDataException();
        }
        return answer;
    }
}
