package ru.job4j.calculator;

/**
 * BaseAction.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction implements Action {
    private final String key;
    private final String name;

    public BaseAction(final String key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s       to perform the %s;", this.key, this.name);
    }
}
