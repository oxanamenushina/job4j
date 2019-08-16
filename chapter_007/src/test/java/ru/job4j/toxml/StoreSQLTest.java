package ru.job4j.toxml;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StoreSQLTest {

    @Test
    public void whenGenerateThreeElementsThenDataBaseHasThreeElements() {
        try (StoreSQL store = new StoreSQL(new Config())) {
            store.generate(3);
            assertThat(store.load(), is(List.of(new Entry(1), new Entry(2), new Entry(3))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}