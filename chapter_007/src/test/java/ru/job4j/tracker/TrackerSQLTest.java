package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = new Item("test1", "testDescription", 123);
            Item item = tracker.add(first);
            assertThat(tracker.findById(item.getId()).getName(), is("test1"));
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item previous = new Item("test3", "testDescription", 123L);
            Item item1 = tracker.add(previous);
            tracker.replace(item1.getId(), new Item("test4", "testDescription2", 1234L));
            assertThat(tracker.findById(item1.getId()).getName(), is("test4"));
        }
    }

    @Test
    public void whenDeleteIdThenReturnNextName() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = tracker.add(new Item("test5", "testDescription", 123L));
            Item it = new Item("test6", "testDescription2", 1234L);
            it.setComments(List.of("111", "222", "333"));
            Item second = tracker.add(it);
            Item third = tracker.add(new Item("test7", "testDescription3", 12345L));
            third.setComments(List.of("444", "555"));
            tracker.delete(second.getId());
            assertThat(tracker.findAll().get(1).getName(), is("test7"));
        }
    }

    @Test
    public void whenFindAllThenReturnListOfAllItems() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = tracker.add(new Item("test8", "testDescription", 123L));
            Item second = tracker.add(new Item("test9", "testDescription2", 1234L));
            List<Item> list = tracker.findAll();
            assertThat(list.get(0).getName(), is("test8"));
            assertThat(list.get(1).getName(), is("test9"));
        }
    }

    @Test
    public void whenFindByNameThenReturnListOfItemsWithSameName() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = tracker.add(new Item("test10", "testDescription", 123L));
            Item second = tracker.add(new Item("test11", "testDescription2", 1234L));
            Item third = tracker.add(new Item("test10", "testDescription3", 12345L));
            List<Item> list = tracker.findByName("test10");
            assertThat(list.get(0).getName(), is("test10"));
            assertThat(list.get(0).getDesc(), is("testDescription"));
            assertThat(list.get(1).getName(), is("test10"));
            assertThat(list.get(1).getDesc(), is("testDescription3"));
        }
    }

    @Test
    public void whenFindByIdThenReturnThisItem() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        Item first = tracker.add(new Item("test13", "testDescription", 123L));
        Item second = tracker.add(new Item("test14", "testDescription2", 1234L));
        assertThat(tracker.findById(second.getId()).getName(), is("test14"));
    }
}