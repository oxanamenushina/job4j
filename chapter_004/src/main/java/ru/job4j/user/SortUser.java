package ru.job4j.user;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
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
        return list.stream().sorted().collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
    }

    /**
     * Метод возвращает лист пользователей, отсортированный по длине имени.
     * @param list лист
     * @return отсортированный лист пользователей
     */
    public List<User> sortNameLength(List<User> list) {
        return list.stream()
                .sorted((o1, o2) -> o1.getName().length() - o2.getName().length() != 0
                        ? o1.getName().length() - o2.getName().length() : Integer.compare(o1.getAge(), o2.getAge()))
                .collect(Collectors.toList());
    }

    /**
     * Метод возвращает лист пользователей, отсортированный по обоим полям,
     * сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     * @param list лист
     * @return отсортированный лист пользователей
     */
    public List<User> sortByAllFields(List<User> list) {
        return list.stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()) != 0
                        ? o1.getName().compareTo(o2.getName()) : Integer.compare(o1.getAge(), o2.getAge()))
                .collect(Collectors.toList());
    }
}
