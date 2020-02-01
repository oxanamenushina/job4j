package ru.job4j.storage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * UserStorageTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserStorageTest {

    private UserStorage storage;

    @Before
    public void addUsers() throws InterruptedException {
        this.storage = new UserStorage();
        User user1 = new User(1, 10000);
        User user2 = new User(2, 8000);
        User user3 = new User(3, 12000);
        AdditionUsers au = new AdditionUsers(storage, List.of(user1, user2, user3));
        Thread t1 = new Thread(au);
        Thread t2 = new Thread(au);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void whenAddThreeUsersThenStorageHasThreeUsers() {
        assertThat(storage.get(1), is(new User(1, 10000)));
        assertThat(storage.get(2), is(new User(2, 8000)));
        assertThat(storage.get(3), is(new User(3, 12000)));
    }

    @Test
    public void whenUpdateTwoUsersThenTwoUsersUpdated() throws InterruptedException {
        User user4 = new User(1, 2000);
        User user5 = new User(2, 3000);
        Thread t1 = new Thread(() -> storage.update(user4));
        Thread t2 = new Thread(() -> storage.update(user5));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(storage.get(1).getAmount(), is(2000));
        assertThat(storage.get(2).getAmount(), is(3000));
    }

    @Test
    public void whenDeleteTwoUsersThenStorageHasNoUsers() throws InterruptedException {
        Thread t1 = new Thread(() -> storage.delete(new User(1, 10000)));
        Thread t2 = new Thread(() -> storage.delete(new User(2, 8000)));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertNull(storage.get(1));
        assertNull(storage.get(2));
    }

    @Test
    public void whenTransferTwoUsersThenStoreHasNoUsers() throws InterruptedException {
        Thread t1 = new Thread(() -> storage.transfer(1, 2, 1000));
        Thread t2 = new Thread(() -> storage.transfer(3, 1, 5000));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(storage.get(1).getAmount(), is(14000));
        assertThat(storage.get(2).getAmount(), is(9000));
        assertThat(storage.get(3).getAmount(), is(7000));
    }

    /**
     * AdditionUsers.
     */
    public class AdditionUsers implements Runnable {

        private UserStorage storage;
        private List<User> users;

        public AdditionUsers(UserStorage storage, List<User> users) {
            this.storage = storage;
            this.users = users;
        }

        @Override
        public void run() {
            for (User user : this.users) {
                this.storage.add(user);
            }
        }
    }
}