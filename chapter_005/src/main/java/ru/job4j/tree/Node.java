package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class Node<E extends Comparable<E>> {

    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    /**
     * Метод добавляет элемент child в список children.
     * @param child child.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Метод возвращает список дочерних элементов.
     * @return список дочерних элементов.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Метод сравнивает элементы.
     * @param that сравниваемый элемент.
     * @return true - элементы равны, false - нет.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Метод возвращает значение элемента.
     * @return значение элемента.
     */
    public E getValue() {
        return this.value;
    }
}
