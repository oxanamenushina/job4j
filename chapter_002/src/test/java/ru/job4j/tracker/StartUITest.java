package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Test StartUI.
 */
public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUpdateThenTrackerNotUpdateValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", "неправильный ID", "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenTrackerDeleteItem() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(new String[]{"3", first.getId(), "6"});
        Item[] expect = {second};
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(expect));
    }

    @Test
    public void whenDeleteThenTrackerNotDeleteItem() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(new String[]{"3", "неправильный ID", "6"});
        Item[] expect = {first, second};
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(expect));
    }
}
