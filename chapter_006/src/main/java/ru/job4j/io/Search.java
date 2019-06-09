package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Search {
    /**
     * Метод проверяет все каталоги, находящиеся в заданном каталоге
     * и возвращает список всех файлов с заданными расширениями.
     * @param parent путь до каталога, с которого осуществляется поиск.
     * @param exts список расширений файлов, которые нужно найти.
     * @return список всех файлов с заданными расширениями.
     */
    public List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(parent));
        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] files = current.listFiles();
            if (current.isDirectory() && files != null) {
                for (File file : files) {
                    queue.offer(file);
                }
            } else if (exts.contains(current.getName().substring(current.getName().lastIndexOf(".") + 1))) {
                result.add(current);
            }
        }
        return result;
    }
}