package ru.job4j.mail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * UserAssociationTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserAssociationTest {

    @Test
    public void whenTwoUsersConnectThenReturnOneUser() {
        Merger merger = new UserAssociation();
        List<User> users = new ArrayList<>();
        User u1 = new MailUser("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        User u2 = new MailUser("user2", Set.of("xxx@ya.ru", "ups@pisem.net"));
        users.add(u1);
        users.add(u2);
        List<User> result = merger.merge(users);
        assertThat(result, is(List.of(u2)));
        assertThat(result.get(0).getAllMails(),
                containsInAnyOrder("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net"));
    }

    @Test
    public void whenTwoUsersDoNotConnectThenReturnTwoUsers() {
        Merger merger = new UserAssociation();
        List<User> users = new ArrayList<>();
        User u1 = new MailUser("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        User u2 = new MailUser("user2", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        users.add(u1);
        users.add(u2);
        List<User> result = merger.merge(users);
        assertThat(result, is(List.of(u1, u2)));
        assertThat(result.get(0).getAllMails(),
                containsInAnyOrder("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        assertThat(result.get(1).getAllMails(),
                containsInAnyOrder("ups@pisem.net", "aaa@bbb.ru"));
    }

    @Test
    public void whenFiveUsersConnectThenReturnTwoUsers() {
        Merger merger = new UserAssociation();
        List<User> users = new ArrayList<>();
        User u1 = new MailUser("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        User u2 = new MailUser("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        User u3 = new MailUser("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        User u4 = new MailUser("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        User u5 = new MailUser("user5", Set.of("xyz@pisem.net"));
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        List<User> result = merger.merge(users);
        assertThat(result, is(List.of(u4, u5)));
        assertThat(result.get(0).getAllMails(),
                containsInAnyOrder("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"));
        assertThat(result.get(1).getAllMails(),
                containsInAnyOrder("xyz@pisem.net", "vasya@pupkin.com"));
    }
}