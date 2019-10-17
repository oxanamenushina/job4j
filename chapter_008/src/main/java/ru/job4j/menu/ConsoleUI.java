package ru.job4j.menu;

import java.util.Scanner;
import java.util.Set;

/**
 * ConsoleUI.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ConsoleUI implements Input {

    private Scanner scan = new Scanner(System.in);
    private Set<String> numbers;

    public ConsoleUI(Set<String> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String ask(String question) throws IncorrectDataException {
        System.out.print(question);
        String answer = this.scan.nextLine();
        answer = ("e".equalsIgnoreCase(answer)) ? answer : answer.endsWith(".") ? answer : answer + ".";
        if (!this.numbers.contains(answer) && (!"e".equalsIgnoreCase(answer))) {
            throw new IncorrectDataException();
        }
        return answer;
    }
}