package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {
    /**
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new UpdateItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
        this.actions.add(new ExitProgram());
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
                System.out.println(action.info());
            }
        }
    }

    /**
     * Добавление новой заявки.
     */
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с ID: " + item.getId() + "-----------");
            System.out.println("------------ Имя новой заявки: " + item.getName() + "-----------");
            System.out.println("------------ Описание новой заявки: " + item.getDesc() + "-----------");
        }

        @Override
        public String info() {
            return "Add new item.";
        }
    }

    /**
     * Вывод всех даявок.
     */
    private class ShowItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Вывод всех заявок --------------");
            Item[] items = tracker.findAll();
            for (Item item : items) {
                System.out.println("Заявка с ID: " + item.getId());
                System.out.println("Имя заявки: " + item.getName());
                System.out.println("Описание заявки: " + item.getDesc());
            }
        }

        @Override
        public String info() {
            return "Show all Items.";
        }
    }

    /**
     * Редактирование заявки.
     */
    private class UpdateItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование заявки --------------");
            String id = input.ask("Введите ID заявки :");
            String name = input.ask("Введите имя новой заявки :");
            String desc = input.ask("Введите описание новой заявки :");
            Item item = new Item(name, desc);
            boolean result = tracker.replace(id, item);
            if (result) {
                System.out.println("------------ Заявка с ID : " + id
                        + " заменена на новую заявку с ID: " + item.getId() + "-----------");
            } else {
                System.out.println("------------ Заявка с ID : " + id + " не заменена на новую -----------");
            }
        }

        @Override
        public String info() {
            return "Edit item.";
        }
    }

    /**
     * Удаление заявки.
     */
    private class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите ID заявки :");
            boolean result = tracker.delete(id);
            if (result) {
                System.out.println("------------ Заявка с ID : " + id + " удалена -----------");
            } else {
                System.out.println("------------ Заявка не удалена -----------");
            }
        }

        @Override
        public String info() {
            return "Delete Item.";
        }
    }

    /**
     * Поиск заявки по ID.
     */
    private class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по ID --------------");
            String id = input.ask("Введите ID заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("------------ Заявка с ID : " + item.getId() + " найдена -----------");
                System.out.println("Имя заявки: " + item.getName());
                System.out.println("Описание заявки: " + item.getDesc());
            } else {
                System.out.println("------------ Заявка с ID : " + id + " не найдена -----------");
            }
        }

        @Override
        public String info() {
            return "Find item by ID.";
        }
    }

    /**
     * Поиск заявок по имени.
     */
    private class FindItemsByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по имени --------------");
            String name = input.ask("Введите имя заявки :");
            Item[] items = tracker.findByName(name);
            if (items.length != 0) {
                System.out.println("------------ Заявки с именем : " + name + " найдены -----------");
                for (Item item : items) {
                    System.out.println("ID заявки: " + item.getId());
                    System.out.println("Описание заявки: " + item.getDesc());
                }
            } else {
                System.out.println("------------ Заявки с именем : " + name + " не найдены -----------");
            }
        }

        @Override
        public String info() {
            return "Find item by name.";
        }
    }

    /**
     * Выход из программы.
     */
    private class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

        }

        @Override
        public String info() {
            return "Exit program.";
        }
    }
}
