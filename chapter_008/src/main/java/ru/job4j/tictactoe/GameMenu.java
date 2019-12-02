package ru.job4j.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * GameMenu.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class GameMenu implements Menu {

    /**
     * The list of menu items.
     */
    private List<String> menu = new ArrayList<>();

    /**
     * The method returns the list of menu items.
     * @return the list of menu items.
     */
    @Override
    public List<String> getMenu() {
        this.fillMenu();
        return this.menu;
    }

    /**
     * The method fills the menu items.
     */
    private void fillMenu() {
        this.menu.add(new StringBuilder()
                .append("TIC-TAC-TOE GAME")
                .append(System.lineSeparator())
                .toString());
        this.menu.add(new StringBuilder()
                .append(System.lineSeparator())
                .append("Enter \"n\" to start a new game.")
                .append(System.lineSeparator())
                .append("Enter \"e\" to exit.")
                .append(System.lineSeparator())
                .toString());
        this.menu.add(new StringBuilder()
                .append("Enter the size of the playing field")
                .append(System.lineSeparator())
                .append("(the number must be not less than 3 and not more than 99)")
                .append(System.lineSeparator())
                .append("or press enter to create a default playing field.")
                .append(System.lineSeparator())
                .toString());
        this.menu.add(new StringBuilder()
                .append("Enter the figure to play:")
                .append(System.lineSeparator())
                .append("X   - play cross (goes first);")
                .append(System.lineSeparator())
                .append("0   - play zero.")
                .append(System.lineSeparator())
                .toString());
        this.menu.add(new StringBuilder()
                .append("Enter the cell coordinates separated by commas")
                .append(System.lineSeparator())
                .append("(the first is the row number, the second is the column number).")
                .append(System.lineSeparator())
                .toString());
        this.menu.add(new StringBuilder()
                .append(System.lineSeparator())
                .append("You won!")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString());
        this.menu.add(new StringBuilder()
                .append(System.lineSeparator())
                .append("You lost!")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString());
        this.menu.add(new StringBuilder()
                .append("There are no free cells.")
                .append(System.lineSeparator())
                .toString());
    }
}