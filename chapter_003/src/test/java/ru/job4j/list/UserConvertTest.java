package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {
    @Test
    public void whenListThenHashMap() {
        UserConvert convert = new UserConvert();
        User user1 = new User(1, "Vasiliy", "Moscow");
        User user2 = new User(2, "Ivan", "Ryazan");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        HashMap<Integer, User> result = convert.process(list);
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(user1.getId(), user1);
        expect.put(user2.getId(), user2);
        assertThat(result, is(expect));
    }
}
