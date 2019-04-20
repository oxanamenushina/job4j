package ru.job4j.list;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        HashMap<Integer, User> result = convert.process(List.of(user1, user2));
        assertThat(result, is(Map.of(user1.getId(), user1, user2.getId(), user2)));
    }
}
