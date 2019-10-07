package ru.job4j.calculator;

import java.util.Scanner;

/**
 * ConsoleUI.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ConsoleUI implements Input {

    private Scanner scan = new Scanner(System.in);
    private CheckInput ci;

    public ConsoleUI(CheckInput ci) {
        this.ci = ci;
    }

    @Override
    public String ask(String question, int number, boolean isFirst, String operation, double lastRes)
            throws IncorrectDataException {
        System.out.print(question);
        String answer = this.scan.nextLine();
        if (!this.ci.checkAnswer(answer, number, isFirst, operation, lastRes)) {
            throw new IncorrectDataException();
        }
        return answer;
    }

}
