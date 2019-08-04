package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class TrackerSQLTest {

    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item first = new Item("test1", "testDescription", 123);
            Item item = tracker.add(first);
            assertThat(tracker.findById(item.getId()).getName(), is("test1"));
            tracker.delete(item.getId());
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item previous = new Item("test3", "testDescription", 123L);
            Item item1 = tracker.add(previous);
            tracker.replace(item1.getId(), new Item("test4", "testDescription2", 1234L));
            assertThat(tracker.findById(item1.getId()).getName(), is("test4"));
            tracker.delete(item1.getId());
        }
    }

    @Test
    public void whenDeleteIdThenReturnNextName() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item first = tracker.add(new Item("test5", "testDescription", 123L));
            Item second = tracker.add(new Item("test6", "testDescription2", 1234L));
            Item third = tracker.add(new Item("test7", "testDescription3", 12345L));
            tracker.delete(second.getId());
            assertThat(tracker.findAll().get(1).getName(), is("test7"));
            tracker.delete(first.getId());
            tracker.delete(third.getId());
        }
    }

    @Test
    public void whenFindAllThenReturnListOfAllItems() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item first = tracker.add(new Item("test8", "testDescription", 123L));
            Item second = tracker.add(new Item("test9", "testDescription2", 1234L));
            List<Item> list = tracker.findAll();
            assertThat(list.get(0).getName(), is("test8"));
            assertThat(list.get(1).getName(), is("test9"));
            tracker.delete(first.getId());
            tracker.delete(second.getId());
        }
    }

    @Test
    public void whenFindByNameThenReturnListOfItemsWithSameName() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item first = tracker.add(new Item("test10", "testDescription", 123L));
            Item second = tracker.add(new Item("test11", "testDescription2", 1234L));
            Item third = tracker.add(new Item("test10", "testDescription3", 12345L));
            List<Item> list = tracker.findByName("test10");
            assertThat(list.get(0).getName(), is("test10"));
            assertThat(list.get(0).getDesc(), is("testDescription"));
            assertThat(list.get(1).getName(), is("test10"));
            assertThat(list.get(1).getDesc(), is("testDescription3"));
            tracker.delete(first.getId());
            tracker.delete(second.getId());
            tracker.delete(third.getId());
        }
    }

    @Test
    public void whenFindByIdThenReturnThisItem() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        Item first = tracker.add(new Item("test13", "testDescription", 123L));
        Item second = tracker.add(new Item("test14", "testDescription2", 1234L));
        assertThat(tracker.findById(second.getId()).getName(), is("test14"));
        tracker.delete(first.getId());
        tracker.delete(second.getId());
    }
}