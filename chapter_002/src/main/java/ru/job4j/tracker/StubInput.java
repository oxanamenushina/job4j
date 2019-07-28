package ru.job4j.tracker;

import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    private final List<String> value;
    private int position;

    public StubInput(final List<String> value) {
        this.value = value;
    }

    /**
     * Метод заменяет ответы пользователя.
     * @param question
     * Ответ пользователя.
     */
    @Override
    public String ask(String question) {
        return this.value.get(this.position++);
    }

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
