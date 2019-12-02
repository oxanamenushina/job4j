package ru.job4j.tictactoe;

/**
 * ValidateInput.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input {

    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question, int number) {
        boolean rst = true;
        String answer = "";
        while (rst) {
            try {
                answer = this.input.ask(question, number);
                rst = false;
            } catch (IncorrectDataException e) {
                System.out.print("Please enter validate data again." + System.lineSeparator());
            }
        }
        return answer;
    }
}