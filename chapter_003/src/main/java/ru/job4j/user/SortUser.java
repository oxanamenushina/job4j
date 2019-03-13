package ru.job4j.user;

import java.io.File;
import java.util.*;

/**
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * Метод возвращает TreeSet пользователей, отсортированный по возрасту в порядке возрастания.
     * @param list лист
     * @return TreeSet пользователей
     */
    public Set<User> sort(List<User> list) {
        Set<User> users = new TreeSet<>(list);
        return users;
    }

    /**
     * Метод возвращает лист пользователей, отсортированный по длине имени.
     * @param list лист
     * @return отсортированный лист пользователей
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = Integer.compare(o1.getName().length(), o2.getName().length());
                return result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return list;
    }

    /**
     * Метод возвращает лист пользователей, отсортированный по обоим полям,
     * сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     * @param list лист
     * @return отсортированный лист пользователей
     */
    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return list;
    }
}
