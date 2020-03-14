package ru.job4j.bomberman2;

/**
 * Controllability.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Controllability<T> {

    /**
     * The method adds task.
     * @param task
     */
    void addTask(T task);
}