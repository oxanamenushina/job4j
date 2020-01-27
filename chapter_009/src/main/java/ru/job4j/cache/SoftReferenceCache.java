package ru.job4j.cache;

import java.util.function.Function;

/**
 * SoftReferenceCache.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SoftReferenceCache implements Cache<String, String, Function<String, String>> {

    /**
     * Cache.
     */
    private Cache<String, String, Function<String, String>> cache;

    public SoftReferenceCache(String path) {
        IReader<String, String> reader = new TextReader(path);
        this.cache = new CacheEmulation<>(reader::read);
    }

    @Override
    public String get(String key) {
        return this.cache.get(key);
    }
}