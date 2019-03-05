package ru.job4j.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    @Test
    public void whenListOf5UsersThenSortedTreeSetOf5Users() {
        List<User> list = new ArrayList<>(Arrays.asList(
                new User("Vasiliy", 27), new User("Anton", 36), new User("Ivan", 25),
                new User("Inna", 15), new User("Alena", 27)));
        Set<User> users = new SortUser().sort(list);
        User[] expect = {new User("Inna", 15), new User("Ivan", 25),
                new User("Alena", 27), new User("Vasiliy", 27), new User("Anton", 36)};
        assertThat(users.toArray(new User[0]), is(expect));
    }

    @Test
    public void whenListOf4UsersThenSortedTreeSetOf4Users() {
        List<User> list = new ArrayList<>(Arrays.asList(new User("Vasiliy", 50),
                new User("Anton", 28), new User("Ivan", 21), new User("Alena", 39)));
        Set<User> users = new SortUser().sort(list);
        User[] expect = {new User("Ivan", 21), new User("Anton", 28),
                new User("Alena", 39), new User("Vasiliy", 50)};
        assertThat(users.toArray(new User[0]), is(expect));
    }
}
