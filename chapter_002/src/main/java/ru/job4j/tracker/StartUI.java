package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Input input;
    private final Tracker tracker;
    private int[] range;
    private boolean exit = false;

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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        range = new int[menu.getActionsLentgh()];
        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range[i] = i;
        }
        do {
            menu.show();
            menu.select(input.ask("select:", range));
        } while (!this.exit);
    }

    /**
     * Устанавливает значение true для поля exit
     */
    public void closeProgram() {
        this.exit = true;
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input, new Tracker()).init();
    }
}
