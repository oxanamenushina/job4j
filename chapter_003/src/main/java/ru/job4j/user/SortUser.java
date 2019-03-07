package ru.job4j.user;

import java.util.*;

/**
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * Метод возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
     * @param list лист
     * @return TreeSet пользователей
     */
    public Set<User> sort(List<User> list) {
        Set<User> users = new TreeSet<>(list);
        return users;
    }
}
