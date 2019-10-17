package ru.job4j.menu;

import java.util.List;
import java.util.Set;

/**
 * StubInput.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    private List<String> answers;
    private Set<String> numbers;
    private int position = 0;

    public StubInput(List<String> answers, Set<String> numbers) {
        this.answers = answers;
        this.numbers = numbers;
    }

    @Override
    public String ask(String question) {
        String answer = this.answers.get(position++);
        if (!numbers.contains(answer)  && (!"e".equalsIgnoreCase(answer))) {
            throw new IncorrectDataException();
        }
        return answer;
    }
}
