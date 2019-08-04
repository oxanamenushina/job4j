package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test StartUI.
 */
public class StartUITest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    private StringBuilder menu = new StringBuilder()
            .append("0 : Добавление новой заявки")
            .append(System.lineSeparator())
            .append("1 : Вывод всех заявок")
            .append(System.lineSeparator())
            .append("2 : Редактирование заявки")
            .append(System.lineSeparator())
            .append("3 : Удаление заявки")
            .append(System.lineSeparator())
            .append("4 : Поиск заявки по ID")
            .append(System.lineSeparator())
            .append("5 : Поиск заявки по имени")
            .append(System.lineSeparator())
            .append("6 : Выход из программы")
            .append(System.lineSeparator());

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(System.out);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        ITracker tracker = new Tracker();
        Input input = new StubInput(List.of("0", "test name", "desc", "6"));
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        ITracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(List.of("2", item.getId(), "test replace", "заменили заявку", "6"));
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUpdateThenTrackerNotUpdateValue() {
        ITracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(List.of("2", "неправильный ID", "test replace", "заменили заявку", "6"));
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenTrackerDeleteItem() {
        ITracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(List.of("3", first.getId(), "6"));
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll(), is(List.of(second)));
    }

    @Test
    public void whenDeleteThenTrackerNotDeleteItem() {
        ITracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(List.of("3", "неправильный ID", "6"));
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll(), is(List.of(first, second)));
    }

    @Test
    public void whenFindAllThenTrackerShowAllItems() {
        ITracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(List.of("1", "6"));
        new StartUI(input, tracker, output).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu.toString())
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
                                .append(menu.toString())
                                .append("------------ Выход из программы --------------")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByIdThenTrackerShowItemWithSameId() {
        ITracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(List.of("4", second.getId(), "6"));
        new StartUI(input, tracker, output).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu.toString())
                                .append("------------ Заявка с ID : ")
                                .append(second.getId())
                                .append(" найдена -----------")
                                .append(System.lineSeparator())
                                .append("Имя заявки: test name2")
                                .append(System.lineSeparator())
                                .append("Описание заявки: desc2")
                                .append(System.lineSeparator())
                                .append(menu.toString())
                                .append("------------ Выход из программы --------------")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenTrackerShowItemWithSameName() {
        ITracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "desc1"));
        Item second = tracker.add(new Item("test name1", "desc2"));
        Item third = tracker.add(new Item("test name3", "desc3"));
        Input input = new StubInput(List.of("5", "test name1", "6"));
        new StartUI(input, tracker, output).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menu.toString())
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
                                .append(menu.toString())
                                .append("------------ Выход из программы --------------")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
