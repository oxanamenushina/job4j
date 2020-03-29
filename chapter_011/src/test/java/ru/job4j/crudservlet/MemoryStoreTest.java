package ru.job4j.crudservlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * MemoryStoreTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MemoryStoreTest {

    /**
     * The instance of MemoryStore class.
     */
    private final Store store = MemoryStore.getInstance();

    @Before
    public void addUsers() {
        store.add(new User("Max", "max111", "max123@mail.ru"));
        store.add(new User("Den", "den222", "den25@bk.ru"));
    }

    @After
    public void clearStore() {
        store.findAll().forEach(store::delete);
    }

    @Test
    public void whenAddUserThenMemoryStoreContainsThisUser() {
        User u1 = new User("Oleg", "Oleg333", "oleg@gmail.ru");
        store.add(u1);
        assertThat(store.findById(u1.getId()), is(u1));
    }

    @Test
    public void whenUpdateUserThenUserUpdated() {
        int id = store.findAll().get(0).getId();
        store.update(new User(id, null, "M444", null));
        assertThat(store.findById(id).getLogin(), is("M444"));
    }

    @Test
    public void whenDeleteUserThenMemoryStoreHasNoThisUser() {
        int id = store.findAll().get(0).getId();
        store.delete(new User(id, null, null, null));
        assertThat(store.findAll().size(), is(1));
    }

    @Test
    public void whenFindAllThenReturnAllUsers() {
        User u1 = new User("Ilya", "Ilya5", "ilya555@gmail.ru");
        store.add(u1);
        assertThat(store.findAll().size(), is(3));
    }

    @Test
    public void whenFindByIdThenReturnUserWithSameId() {
        User u1 = new User("Ilya", "Ilya5", "ilya555@gmail.ru");
        store.add(u1);
        assertThat(store.findById(u1.getId()), is(u1));
    }
}