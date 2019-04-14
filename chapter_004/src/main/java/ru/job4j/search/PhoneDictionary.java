package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедщих пользователей.
     */
    public List<Person> find(String key) {
        return this.persons.stream()
                .filter((person) -> person.getName().contains(key) || person.getSurname().contains(key)
                        || person.getPhone().contains(key) || person.getAddress().contains(key))
                .collect(Collectors.toList());
    }
}
