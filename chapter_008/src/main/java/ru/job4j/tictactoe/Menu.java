package ru.job4j.tictactoe;

import java.util.List;

/**
 * Menu.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Menu {

    /**
     * The method returns the list of menu items.
     * @return the list of menu items.
     */
    List<String> getMenu();
}
