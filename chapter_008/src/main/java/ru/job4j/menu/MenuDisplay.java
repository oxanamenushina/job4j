package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuDisplay.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MenuDisplay implements Display {

    private Menu menu;
    private List<String> text = new ArrayList<>();

    public MenuDisplay(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<String> getTextToDisplay() {
        this.fillTextToDisplay();
        return this.text;
    }

    /**
     * The method creates menu text.
     * @return menu text.
     */
    private String getMenuText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu:").append(System.lineSeparator());
        String indent = "   ";
        this.menu.getMenu().forEach((key, value) -> sb
                .append(indent.repeat(key.length() / 2 - 1))
                .append(key)
                .append(value.getName())
                .append(System.lineSeparator()));
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * The method return questions for users.
     * @return questions for users.
     */
    private String askAction() {
        return new StringBuilder()
                .append("Enter the number of the menu element to perform the action")
                .append(System.lineSeparator())
                .append("or enter \"e\" to exit.")
                .append(System.lineSeparator())
                .toString();
    }

    /**
     * The method fills the text to display.
     */
    private void fillTextToDisplay() {
        this.text.add(this.getMenuText());
        this.text.add(this.askAction());
    }
}