package ru.job4j.cache;

import java.io.File;
import java.util.function.Function;

/**
 * StartCache.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StartCache {

    public static void main(String[] args) {
        Cache<String, String, Function<String, String>> cache
                = new SoftReferenceCache(new File("chapter_009").getAbsolutePath());
        System.out.println(cache.get("file1.txt"));
        System.out.println(cache.get("file2.txt"));
    }
}