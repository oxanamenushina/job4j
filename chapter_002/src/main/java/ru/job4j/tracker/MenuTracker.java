package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private Consumer<String> output;

    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     * @param input объект типа Input.
     * @param tracker объект типа Tracker.
     * @param output объект типа Consumer<String>.
     */
    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Метод для получения массива меню.
     * @return длину массива.
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Добавление новой заявки"));
        this.actions.add(new ShowItems(1, "Вывод всех заявок"));
        this.actions.add(new UpdateItem(2, "Редактирование заявки"));
        this.actions.add(new DeleteItem(3, "Удаление заявки"));
        this.actions.add(new FindItemById(4, "Поиск заявки по ID"));
        this.actions.add(new FindItemsByName(5, "Поиск заявки по имени"));
        this.actions.add(new ExitProgram(ui, 6, "Выход из программы"));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                this.output.accept(action.info());
            }
        }
    }

    /**
     * Добавление новой заявки.
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept(String.format("------------ Новая заявка с ID: %s -----------%n"
                            + "------------ Имя новой заявки: %s -----------%n"
                            + "------------ Описание новой заявки: %s -----------",
                    item.getId(), item.getName(), item.getDesc()));
        }
    }

    /**
     * Вывод всех даявок.
     */
    private class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            List<Item> items = tracker.findAll();
            for (Item item : items) {
                output.accept(String.format("Заявка с ID: %s%nИмя заявки: %s%nОписание заявки: %s",
                        item.getId(), item.getName(), item.getDesc()));
            }
        }
    }

    /**
     * Редактирование заявки.
     */
    private class UpdateItem extends BaseAction {

        public UpdateItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки :");
            String name = input.ask("Введите имя новой заявки :");
            String desc = input.ask("Введите описание новой заявки :");
            Item item = new Item(name, desc);
            boolean result = tracker.replace(id, item);
            if (result) {
                output.accept(String.format("------------ Заявка с ID : %s заменена на новую заявку с ID: %s -----------",
                        id, item.getId()));
            } else {
                output.accept(String.format("------------ Заявка с ID : %s не заменена на новую -----------", id));
            }
        }
    }

    /**
     * Удаление заявки.
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки :");
            boolean result = tracker.delete(id);
            if (result) {
                output.accept(String.format("------------ Заявка с ID : %s удалена -----------", id));
            } else {
                output.accept("------------ Заявка не удалена -----------");
            }
        }
    }

    /**
     * Поиск заявки по ID.
     */
    private class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                output.accept(String.format("------------ Заявка с ID : %s найдена -----------%nИмя заявки: %s%nОписание заявки: %s",
                        item.getId(), item.getName(), item.getDesc()));
            } else {
                output.accept(String.format("------------ Заявка с ID : %s не найдена -----------", id));
            }
        }
    }

    /**
     * Поиск заявок по имени.
     */
    private class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки :");
            List<Item> items = tracker.findByName(name);
            if (items.size() != 0) {
                for (Item item : items) {
                    output.accept(String.format("ID заявки: %s%nОписание заявки: %s", item.getId(), item.getDesc()));
                }
            } else {
                output.accept(String.format("------------ Заявки с именем : %s не найдены -----------", name));
            }
        }
    }

    /**
     * Выход из программы.
     */
    private class ExitProgram extends BaseAction {

        private StartUI ui;

        public ExitProgram(StartUI ui, int key, String name) {
            super(key, name);
            this.ui = ui;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Выход из программы --------------");
            this.ui.closeProgram();
        }
    }
}
