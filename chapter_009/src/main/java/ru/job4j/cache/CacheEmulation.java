package ru.job4j.cache;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * CacheEmulation.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CacheEmulation<K, V, T extends Function<K, V>> implements Cache<K, V, T> {

    /**
     * Cache.
     */
    private Map<K, Reference<V>> cache = new HashMap<>();

    /**
     * Reader.
     */
    private T reader;

    public CacheEmulation(T reader) {
        this.reader = reader;
    }

    /**
     * The method reads the value,
     * wraps it in SoftReference
     * and puts it in the cache.
     * @param key - key.
     * @return value.
     */
    private V put(K key) {
        V value = null;
        if (key != null) {
            value = this.reader.apply(key);
            this.cache.put(key, new SoftReference<>(value));
        }
        return value;
    }

    /**
     * The method returns the value from the cache.
     * data from a given source.
     * @param key - key.
     * @return value.
     */
    @Override
    public V get(K key) {
        return this.cache.containsKey(key) ? this.cache.get(key).get() : this.put(key);
    }
}