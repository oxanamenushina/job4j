package ru.job4j.cache;

/**
 * IReader.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface IReader<T, V> {

    /**
     * The method reads and returns
     * data from a given source.
     * @param name - source.
     * @return data.
     */
    V read(T name);
}