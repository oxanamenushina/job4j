package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод считывает из файла и добавляет пары "ключ-значение" в Map values.
     */
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String str;
            while ((str = br.readLine()) != null) {
                int ind = str.indexOf("=");
                if (ind >= 0) {
                    this.values.put(str.substring(0, ind), str.substring(ind + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает значение по заданному ключу.
     * @param key ключ.
     * @return значение, соответствующее заданному ключу.
     */
    public String value(String key) {
        return this.values.get(key);
    }
}
