package ru.job4j.menu;

import java.util.List;
import java.util.Map;

/**
 * Menu.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Menu {

    /**
     * The method adds elements to the menu.
     * @param elements - elements.
     */
    void addAll(List<Element> elements);

    /**
     * The method returns a map of the elements,
     * where key is the element number,
     * value is the element.
     * @return - a map of the elements.
     */
    Map<String, Element> getMenu();
}