package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction implements UserAction {

    private final int key;
    private final String name;

    /**
     * Конструктор.
     * @param key ключ пункта меню.
     * @param name название пункта меню.
     */
    public BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s : %s", this.key, this.name);
    }
}
