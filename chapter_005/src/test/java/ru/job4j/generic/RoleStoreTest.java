package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RoleStoreTest {

    @Test
    public void whenAddUserThenUserStoreHasThisUser() {
        RoleStore<Role> roleStore = new RoleStore<>(new SimpleArray<>(5));
        Role role1 = new Role("123");
        Role role2 = new Role("456");
        roleStore.add(role1);
        roleStore.add(role2);
        assertThat(roleStore.findById("456"), is(role2));
        assertThat(roleStore.findById("123"), is(role1));
    }

    @Test
    public void whenReplaceUserThenUserStoreChangeUser() {
        RoleStore<Role> roleStore = new RoleStore<>(new SimpleArray<>(5));
        Role role1 = new Role("123");
        Role role2 = new Role("456");
        roleStore.add(role1);
        roleStore.add(role2);
        Role role3 = new Role("789");
        roleStore.replace("456", role3);
        assertThat(roleStore.findById("789"), is(role3));
    }

    @Test
    public void whenDeleteUserThenThisUserDeleteFromUserStore() {
        RoleStore<Role> roleStore = new RoleStore<>(new SimpleArray<>(5));
        Role role1 = new Role("123");
        Role role2 = new Role("456");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.delete("456");
        Role role3 = new Role("789");
        assertThat(roleStore.replace("Kate", role3), is(false));
    }

    @Test
    public void whenFindByIdThenReturnUserWithThisId() {
        RoleStore<Role> roleStore = new RoleStore<>(new SimpleArray<>(5));
        Role role1 = new Role("123");
        Role role2 = new Role("456");
        roleStore.add(role1);
        roleStore.add(role2);
        assertThat(roleStore.findById("123"), is(role1));
    }
}