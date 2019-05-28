package ru.job4j.tree;

import java.util.Optional;

/**
 * @version $Id$
 * @since 0.1
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Добавляет элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return true - элемент добавился, false - нет.
     */
    boolean add(E parent, E child);

    /**
     * Метод осуществляет поиск элемента в дереве.
     * @param value искомый элемент.
     * @return Optional<Node<E>>.
     */
    Optional<Node<E>> findBy(E value);
}
