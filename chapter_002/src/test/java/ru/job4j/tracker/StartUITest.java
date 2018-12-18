package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test StartUI.
 */
public class StartUITest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

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

    @Test
    public void whenFindAllThenTrackerShowAllItems() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.")
                                .append(System.lineSeparator())
                                .append("0 - Добавить новую заявку.")
                                .append(System.lineSeparator())
                                .append("1 - Показать все заявки.")
                                .append(System.lineSeparator())
                                .append("2 - Редактировать заявку.")
                                .append(System.lineSeparator())
                                .append("3 - Удалить заявку.")
                                .append(System.lineSeparator())
                                .append("4 - Найти заявку по ID.")
                                .append(System.lineSeparator())
                                .append("5 - Найти заявку по имени.")
                                .append(System.lineSeparator())
                                .append("6 - Выйти из программы.")
                                .append(System.lineSeparator())
                                .append("------------ Вывод всех заявок --------------")
                                .append(System.lineSeparator())
                                .append("Заявка с ID: ")
                                .append(first.getId())
                                .append(System.lineSeparator())
                                .append("Имя заявки: test name1")
                                .append(System.lineSeparator())
                                .append("Описание заявки: desc1")
                                .append(System.lineSeparator())
                                .append("Заявка с ID: ")
                                .append(second.getId())
                                .append(System.lineSeparator())
                                .append("Имя заявки: test name2")
                                .append(System.lineSeparator())
                                .append("Описание заявки: desc2")
                                .append(System.lineSeparator())
                                .append("Меню.")
                                .append(System.lineSeparator())
                                .append("0 - Добавить новую заявку.")
                                .append(System.lineSeparator())
                                .append("1 - Показать все заявки.")
                                .append(System.lineSeparator())
                                .append("2 - Редактировать заявку.")
                                .append(System.lineSeparator())
                                .append("3 - Удалить заявку.")
                                .append(System.lineSeparator())
                                .append("4 - Найти заявку по ID.")
                                .append(System.lineSeparator())
                                .append("5 - Найти заявку по имени.")
                                .append(System.lineSeparator())
                                .append("6 - Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByIdThenTrackerShowItemWithSameId() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(new String[]{"4", second.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.")
                                .append(System.lineSeparator())
                                .append("0 - Добавить новую заявку.")
                                .append(System.lineSeparator())
                                .append("1 - Показать все заявки.")
                                .append(System.lineSeparator())
                                .append("2 - Редактировать заявку.")
                                .append(System.lineSeparator())
                                .append("3 - Удалить заявку.")
                                .append(System.lineSeparator())
                                .append("4 - Найти заявку по ID.")
                                .append(System.lineSeparator())
                                .append("5 - Найти заявку по имени.")
                                .append(System.lineSeparator())
                                .append("6 - Выйти из программы.")
                                .append(System.lineSeparator())
                                .append("------------ Поиск заявки по ID --------------")
                                .append(System.lineSeparator())
                                .append("------------ Заявка с ID : ")
                                .append(second.getId())
                                .append(" найдена -----------")
                                .append(System.lineSeparator())
                                .append("Имя заявки: test name2")
                                .append(System.lineSeparator())
                                .append("Описание заявки: desc2")
                                .append(System.lineSeparator())
                                .append("Меню.")
                                .append(System.lineSeparator())
                                .append("0 - Добавить новую заявку.")
                                .append(System.lineSeparator())
                                .append("1 - Показать все заявки.")
                                .append(System.lineSeparator())
                                .append("2 - Редактировать заявку.")
                                .append(System.lineSeparator())
                                .append("3 - Удалить заявку.")
                                .append(System.lineSeparator())
                                .append("4 - Найти заявку по ID.")
                                .append(System.lineSeparator())
                                .append("5 - Найти заявку по имени.")
                                .append(System.lineSeparator())
                                .append("6 - Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerShowItemWithSameName() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name1", "desc2"));
        Item third = tracker.add(new Item("test name3", "desc3"));
        Input input = new StubInput(new String[]{"5", "test name1", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.")
                                .append(System.lineSeparator())
                                .append("0 - Добавить новую заявку.")
                                .append(System.lineSeparator())
                                .append("1 - Показать все заявки.")
                                .append(System.lineSeparator())
                                .append("2 - Редактировать заявку.")
                                .append(System.lineSeparator())
                                .append("3 - Удалить заявку.")
                                .append(System.lineSeparator())
                                .append("4 - Найти заявку по ID.")
                                .append(System.lineSeparator())
                                .append("5 - Найти заявку по имени.")
                                .append(System.lineSeparator())
                                .append("6 - Выйти из программы.")
                                .append(System.lineSeparator())
                                .append("------------ Поиск заявки по имени --------------")
                                .append(System.lineSeparator())
                                .append("------------ Заявки с именем : test name1 найдены -----------")
                                .append(System.lineSeparator())
                                .append("ID заявки: ")
                                .append(first.getId())
                                .append(System.lineSeparator())
                                .append("Описание заявки: desc1")
                                .append(System.lineSeparator())
                                .append("ID заявки: ")
                                .append(second.getId())
                                .append(System.lineSeparator())
                                .append("Описание заявки: desc2")
                                .append(System.lineSeparator())
                                .append("Меню.")
                                .append(System.lineSeparator())
                                .append("0 - Добавить новую заявку.")
                                .append(System.lineSeparator())
                                .append("1 - Показать все заявки.")
                                .append(System.lineSeparator())
                                .append("2 - Редактировать заявку.")
                                .append(System.lineSeparator())
                                .append("3 - Удалить заявку.")
                                .append(System.lineSeparator())
                                .append("4 - Найти заявку по ID.")
                                .append(System.lineSeparator())
                                .append("5 - Найти заявку по имени.")
                                .append(System.lineSeparator())
                                .append("6 - Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
