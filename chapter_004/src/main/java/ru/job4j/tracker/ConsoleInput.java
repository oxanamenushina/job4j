package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;
/**
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    /**
     * Метод выводит на консоль вопрос и принимает значение с консоли.
     * @param question
     * @return ответ пользователя.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Метод выводит на консоль вопрос, принимает значение с консоли
     * и проверяет входит ли значение в заданный диапазон.
     * @param question
     * @param range
     * @return ответ пользователя.
     */
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException();
        }
        return key;
    }
}
