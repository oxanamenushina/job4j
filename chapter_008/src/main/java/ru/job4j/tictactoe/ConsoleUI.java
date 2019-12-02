package ru.job4j.tictactoe;

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
    CheckInput ci;

    public ConsoleUI(CheckInput ci) {
        this.ci = ci;
    }
    /**
     * The method outputs the question and gets the answer.
     * @param question - question.
     * @param number - question index from the menu list.
     * @return the answer to the question.
     */
    @Override
    public String ask(String question, int number) throws IncorrectDataException {
        System.out.print(question);
        String answer = this.scan.nextLine();
        if (!this.ci.check(answer, number)) {
            throw new IncorrectDataException();
        }
        return answer;
    }
}