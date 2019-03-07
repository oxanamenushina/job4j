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
        User user1 = new User("Vasiliy", 27);
        User user2 = new User("Anton", 36);
        User user3 = new User("Ivan", 25);
        User user4 = new User("Inna", 15);
        User user5 = new User("Alena", 27);
        Set<User> users = new SortUser().sort(new ArrayList<>(Arrays.asList(user1, user2, user3, user4, user5)));
        assertThat(users.toArray(new User[0]), is(new User[]{user4, user3, user5, user1, user2}));
    }

    @Test
    public void whenListOf4UsersThenSortedTreeSetOf4Users() {
        User user1 = new User("Vasiliy", 50);
        User user2 = new User("Anton", 28);
        User user3 = new User("Ivan", 21);
        User user4 = new User("Alena", 39);
        Set<User> users = new SortUser().sort(new ArrayList<>(Arrays.asList(user1, user2, user3, user4)));
        assertThat(users.toArray(new User[0]), is(new User[]{user3, user2, user4, user1}));
    }

    @Test
    public void whenListOf5UsersThenListSortByNameLengthOf5Users() {
        User user1 = new User("Vasiliy", 27);
        User user2 = new User("Anton", 36);
        User user3 = new User("Ivan", 25);
        User user4 = new User("Inna", 15);
        User user5 = new User("Alena", 27);
        List<User> users = new SortUser().sortNameLength(new ArrayList<>(Arrays.asList(user1, user2, user3, user4, user5)));
        assertThat(users.toArray(new User[0]), is(new User[]{user4, user3, user5, user2, user1}));
    }

    @Test
    public void whenListOf4UsersThenListSortByNameLengthOf4Users() {
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 30);
        User user3 = new User("Сергей", 20);
        User user4 = new User("Иван", 25);
        List<User> users = new SortUser().sortNameLength(new ArrayList<>(Arrays.asList(user1, user2, user3, user4)));
        assertThat(users.toArray(new User[0]), is(new User[]{user4, user2, user3, user1}));
    }

    @Test
    public void whenListOf5UsersThenListSortByAllFieldsOf5Users() {
        User user1 = new User("Vasiliy", 27);
        User user2 = new User("Anton", 36);
        User user3 = new User("Ivan", 25);
        User user4 = new User("Inna", 15);
        User user5 = new User("Alena", 27);
        List<User> users = new SortUser().sortByAllFields(new ArrayList<>(Arrays.asList(user1, user2, user3, user4, user5)));
        assertThat(users.toArray(new User[0]), is(new User[]{user5, user2, user4, user3, user1}));
    }

    @Test
    public void whenListOf4UsersThenListSortByAllFieldsOf4Users() {
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 30);
        User user3 = new User("Сергей", 20);
        User user4 = new User("Иван", 25);
        List<User> users = new SortUser().sortByAllFields(new ArrayList<>(Arrays.asList(user1, user2, user3, user4)));
        assertThat(users.toArray(new User[0]), is(new User[]{user4, user2, user3, user1}));
    }

}
