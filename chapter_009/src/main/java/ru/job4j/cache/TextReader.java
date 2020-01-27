package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TextReader.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class TextReader implements IReader<String, String> {

    /**
     * The directory in which the files are stored.
     */
    private String path;

    public TextReader(String path) {
        this.path = path;
    }

    /**
     * The method reads and returns
     * text from a given file.
     * @param name - file name.
     * @return text.
     */
    @Override
    public String read(String name) {
        String result = null;
        File file = new File(this.path, name);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String str;
                while ((str = br.readLine()) != null) {
                    text.append(str).append(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = text.toString();
        }
        return result;
    }
}