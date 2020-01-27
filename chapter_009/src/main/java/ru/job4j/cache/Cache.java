package ru.job4j.cache;

/**
 * Cache.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Cache<K, V, T> {

    /**
     * The method returns a value from the cache.
     * data from a given source.
     * @param key - key.
     * @return value.
     */
    V get(K key);
}