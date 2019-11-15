package ru.job4j.generator;

import java.util.Map;

/**
 * Generator.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Generator {

    /**
     * The generator converts the input string with the keys
     * into a string with the values of these keys.
     * @param string - the input string with the keys.
     * @param words - keys with values.
     * @return a string with the values of the input keys.
     */
    String generate(String string, Map<String, String> words);
}
