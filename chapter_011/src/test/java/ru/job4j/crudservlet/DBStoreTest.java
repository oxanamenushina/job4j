package ru.job4j.crudservlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * DBStoreTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DBStoreTest {

    /**
     * The instance of DBStore class.
     */
    private final Store store = DBStore.getInstance();

    @Before
    public void addUsers() {
        store.add(new User("Max", "max111", "111", "max123@mail.ru", Role.User, "111.jpg"));
        store.add(new User("Den", "den222", "222", "den25@bk.ru", Role.User, "222.jpg"));
    }

    @After
    public void clearStore() {
        store.findAll().forEach(store::delete);
    }

    @Test
    public void whenAddUserThenMemoryStoreContainsThisUser() {
        User u1 = new User("Oleg", "Oleg333", "333", "oleg@gmail.ru", Role.User, "333.jpg");
        store.add(u1);
        assertNotNull(store.findAll().stream().filter(u -> u.getEmail().equals("oleg@gmail.ru")).findFirst().get());
    }

    @Test
    public void whenUpdateUserThenUserUpdated() {
        int id = store.findAll().get(0).getId();
        store.update(new User(id, null, "M444", null, null, Role.User, null));
        assertThat(store.findById(id).getLogin(), is("M444"));
    }

    @Test
    public void whenDeleteUserThenMemoryStoreHasNoThisUser() {
        int id = store.findAll().get(0).getId();
        store.delete(new User(id, null, null, null, null, Role.User, null));
        assertThat(store.findAll().size(), is(1));
    }

    @Test
    public void whenFindAllThenReturnAllUsers() {
        User u1 = new User("Ilya", "Ilya5", "444", "ilya555@gmail.ru", Role.User, "444.jpg");
        store.add(u1);
        assertThat(store.findAll().size(), is(3));
    }

    @Test
    public void whenFindByIdThenReturnUserWithSameId() {
        User u1 = new User("Olga", "olga111", "555", "olga555@gmail.ru", Role.User, "555.jpg");
        store.add(u1);
        int id = store.findAll().stream().filter(u -> u.getEmail().equals("olga555@gmail.ru")).findFirst().get().getId();
        assertThat(store.findById(id), is(u1));
    }
}