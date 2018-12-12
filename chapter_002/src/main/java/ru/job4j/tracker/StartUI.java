package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_ID = "4";
    private static final String FIND_NAME = "5";
    private static final String EXIT = "6";

    private final Input input;
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_ID.equals(answer)) {
                this.findId();
            } else if (FIND_NAME.equals(answer)) {
                this.findName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавление новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с ID: " + item.getId() + "-----------");
    }

    /**
     * Метод реализует вывод всех существующих заявок.
     */
    private void showAllItems() {
        System.out.println("------------ Вывод всех заявок --------------");
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            System.out.println("Заявка с ID: " + item.getId());
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDesc());
        }
    }

    /**
     * Метод реализует замену заявки с заданным ID.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите ID заявки :");
        String name = this.input.ask("Введите имя новой заявки :");
        String desc = this.input.ask("Введите описание новой заявки :");
        Item item = new Item(name, desc);
        boolean result = this.tracker.replace(id, item);
        if (result) {
            System.out.println("------------ Заявка с ID : " + id
                    + " заменена на новую заявку с ID: " + item.getId() + "-----------");
        } else {
            System.out.println("------------ Заявка с ID : " + id + " не заменена на новую -----------");
        }
    }
    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите ID заявки :");
        boolean result = this.tracker.delete(id);
        if (result) {
            System.out.println("------------ Заявка с ID : " + id + " удалена -----------");
        } else {
            System.out.println("------------ Заявка не удалена -----------");
        }
    }

    /**
     * Метод реализует поиск заявки по ID.
     */
    private void findId() {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = this.input.ask("Введите ID заявки :");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("------------ Заявка с ID : " + item.getId() + " найдена -----------");
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDesc());
        } else {
            System.out.println("------------ Заявка с ID : " + id + " не найдена -----------");
        }
    }

    /**
     * Метод реализует поиск заявки по имени заявки.
     */
    private void findName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = this.input.ask("Введите имя заявки :");
        Item[] items = this.tracker.findByName(name);
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

    /**
     * Метод выводит на консоль меню.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0 - Добавить новую заявку.");
        System.out.println("1 - Показать все заявки.");
        System.out.println("2 - Редактировать заявку.");
        System.out.println("3 - Удалить заявку.");
        System.out.println("4 - Найти заявку по ID.");
        System.out.println("5 - Найти заявку по имени.");
        System.out.println("6 - Выйти из программы.");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
