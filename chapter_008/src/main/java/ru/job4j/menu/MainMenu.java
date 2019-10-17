package ru.job4j.menu;

import java.util.List;
import java.util.Map;

/**
 * MainMenu.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MainMenu {

    private List<String> text;
    private Input input;
    private Map<String, Element> menuEl;

    public MainMenu(Menu menu, Display display, Input input) {
        this.menuEl = menu.getMenu();
        this.text = display.getTextToDisplay();
        this.input = input;
    }

    /**
     * The method starts the main loop.
     */
    public void init() {
        String answer = this.input.ask(this.text.get(0) + this.text.get(1));
        while (!"e".equalsIgnoreCase(answer)) {
            Element el = this.menuEl.get(answer);
            if (el != null) {
                el.execute();
            }
            answer = this.input.ask(this.text.get(1));
        }
    }
}