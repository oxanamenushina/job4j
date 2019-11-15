package ru.job4j.generator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SimpleGenerator.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleGenerator implements Generator {

    private final Pattern keys = Pattern.compile("[$]\\{([a-zA-Z0-9]+)}");

    @Override
    public String generate(String string, Map<String, String> words) {
        Matcher m = this.keys.matcher(string);
        StringBuilder sb = new StringBuilder();
        Set<String> k = new HashSet<>();
        while (m.find()) {
            if (!words.containsKey(m.group(1))) {
                throw new KeyNotFoundException();
            }
            m.appendReplacement(sb, words.get(m.group(1)));
            k.add(m.group(1));
        }
        if (!k.containsAll(words.keySet())) {
            throw new ExtraKeysException();
        }
        m.appendTail(sb);
        return sb.toString();
    }
}