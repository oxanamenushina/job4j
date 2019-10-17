package ru.job4j.menu;

import java.util.List;

/**
 * Display.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Display {

    /**
     * The method returns a list of strings to display.
     * @return a list of strings.
     */
    List<String> getTextToDisplay();
}