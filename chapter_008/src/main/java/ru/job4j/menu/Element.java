package ru.job4j.menu;

import java.util.Set;

/**
 * Element.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Element extends SimpleElement, Action {

    /**
     * The method returns the parent of the element.
     * @return the parent of the element.
     */
    Element getParent();

    /**
     * The method adds the child of the element.
     * @param child - the child of the element.
     */
    void addChild(Element child);

    /**
     * The method returns the element number.
     * @return the number of the element.
     */
    String getNumber();

    /**
     * The method returns the number of children of the element.
     * @return the number of children of the element.
     */
    int getNumberOfChildren();

    /**
     * The method returns the children of the element.
     * @return the children of the element.
     */
    Set<Element> getChildren();

    /**
     * The method sets the element number.
     * @param number - the element number.
     */
    void setNumber(String number);
}