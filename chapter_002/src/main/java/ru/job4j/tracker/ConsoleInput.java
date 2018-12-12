package ru.job4j.tracker;

import java.util.Scanner;
/**
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    /**
     * Метод выводит на консоль вопрос и принимает значение с консоли.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
