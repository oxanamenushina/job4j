package ru.job4j.menu;

import java.util.*;

/**
 * SummaryMenu.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SummaryMenu implements Menu {

    private Map<String, Element> menu = new TreeMap<>();
    private int number = 1;

    @Override
    public void addAll(List<Element> elems) {
        elems.forEach(this::add);
    }

    @Override
    public Map<String, Element> getMenu() {
        return this.menu;
    }

    /**
     * The method adds an element to the menu.
     * @param el - element.
     */
    private void add(Element el) {
        Element parent = el.getParent();
        if (parent == null) {
            el.setNumber(this.number++ + ".");
        } else {
            el.getParent().addChild(el);
            el.setNumber(String.format("%s%d%s", parent.getNumber(), (el.getParent().getNumberOfChildren()), "."));
        }
        this.menu.put(el.getNumber(), el);
    }
}