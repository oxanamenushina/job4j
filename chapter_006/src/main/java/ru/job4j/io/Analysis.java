package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Analysis {
    /**
     * Метод считывает информацию из файла source,
     * находит диапазоны, когда сервер не работал
     * и записывает эти диапазоны в файл target.
     * @param source имя файла лога.
     * @param target имя файла после анализа.
     */
    public void unavailable(String source, String target) {
        List<String> ranges = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String str;
            StringBuilder range = null;
            while ((str = br.readLine()) != null) {
                if ((str.startsWith("400 ") || str.startsWith("500 ")) && range == null) {
                    range = new StringBuilder(str.substring(4, 12) + ";");
                }
                if ((str.startsWith("200 ") || str.startsWith("300 ")) && range != null) {
                    ranges.add(range.append(str, 4, 12).append(";").append(System.lineSeparator()).toString());
                    range = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            for (String s : ranges) {
                bw.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}