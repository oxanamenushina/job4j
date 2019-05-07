package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserStoreTest {

    @Test
    public void whenAddUserThenUserStoreHasThisUser() {
        UserStore<User> userStore = new UserStore<>(new SimpleArray<>(5));
        User user1 = new User("Ivan");
        User user2 = new User("Kate");
        userStore.add(user1);
        userStore.add(user2);
        assertThat(userStore.findById("Ivan"), is(user1));
        assertThat(userStore.findById("Kate"), is(user2));
    }

    @Test
    public void whenReplaceUserThenUserStoreChangeUser() {
        UserStore<User> userStore = new UserStore<>(new SimpleArray<>(5));
        User user1 = new User("Ivan");
        User user2 = new User("Kate");
        userStore.add(user1);
        userStore.add(user2);
        User user3 = new User("Ann");
        userStore.replace("Kate", user3);
        assertThat(userStore.findById("Ann"), is(user3));
    }

    @Test
    public void whenDeleteUserThenThisUserDeleteFromUserStore() {
        UserStore<User> userStore = new UserStore<>(new SimpleArray<>(5));
        User user1 = new User("Ivan");
        User user2 = new User("Kate");
        userStore.add(user1);
        userStore.add(user2);
        userStore.delete("Kate");
        User user3 = new User("Ann");
        assertThat(userStore.replace("Kate", user3), is(false));
    }

    @Test
    public void whenFindByIdThenReturnUserWithThisId() {
        UserStore<User> userStore = new UserStore<>(new SimpleArray<>(5));
        User user1 = new User("Ivan");
        User user2 = new User("Kate");
        userStore.add(user1);
        userStore.add(user2);
        assertThat(userStore.findById("Ivan"), is(user1));
    }
}