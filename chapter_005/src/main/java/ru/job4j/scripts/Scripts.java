package ru.job4j.scripts;

import java.util.*;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Scripts {

    /**
     * Метод возвращает список всех скриптов, которые нужны для загрузки входящего скрипта.
     * @param ds ассоциативный массив, в котором ключ - номер скрипта,
     * значение - массив из номеров скриптов,
     * которые необходимы для загрузки данного ключа-скрипта.
     * @param scriptId скрипт, для которого нужно найти список скриптов для его загрузки.
     * @return список всех скриптов, которые нужны для загрузки входящего скрипта.
     */
    public List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        Queue<Integer> queue = new LinkedList<>(ds.get(scriptId));
        Set<Integer> set = new LinkedHashSet<>();
        while (!queue.isEmpty()) {
            set.add(queue.peek());
            queue.addAll(ds.get(queue.poll()));
        }
        return new ArrayList<>(set);
    }
}
