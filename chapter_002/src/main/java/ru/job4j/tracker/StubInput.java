package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    private final String[] value;
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * Метод заменяет ответы пользователя.
     * @param question
     * Ответ пользователя.
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

}