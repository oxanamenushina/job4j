package ru.job4j.menu;

import java.util.HashSet;
import java.util.Set;

/**
 * MenuElement.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MenuElement implements Element {

    private String name;
    private Element parent;
    private Set<Element> children = new HashSet<>();
    private String number;
    private Action action;

    public MenuElement(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public MenuElement(String name, Element parent, Action action) {
        this.name = name;
        this.parent = parent;
        this.action = action;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Element getParent() {
        return parent;
    }

    @Override
    public Set<Element> getChildren() {
        return children;
    }

    @Override
    public void addChild(Element child) {
        this.children.add(child);
    }

    @Override
    public int getNumberOfChildren() {
        return this.children.size();
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void execute() {
        if (this.action != null) {
            this.action.execute();
        }
    }
}