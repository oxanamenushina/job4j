package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DynamicLinkedContainer<E> implements Iterable<E> {

    private Node<E> node;
    private int modCount = 0;
    private int expectedModCount = 0;

    /**
     * Метод добавляет элемент в динамический контейнер.
     * @param value добавляемый элемент.
     */
    public void add(E value) {
        Node<E> current = new Node<>(value);
        current.next = this.node;
        this.node = current;
        this.modCount++;
    }

    /**
     * Метод возвращает элемент диннамического контейнера с заданным индексом.
     * @param index индекс элемента.
     * @return элемент динамического контейнера с заданным индексом.
     */
    public E get(int index) {
        Node<E> result = this.node;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    @Override
    public Iterator<E> iterator() {
        expectedModCount = modCount;
        return new DynamicLinkedContainerIterator(this.node);
    }

    /**
     * Класс DynamicListIterator.
     */
    private class DynamicLinkedContainerIterator implements Iterator<E> {

        private Node<E> list;
        private int position = 0;

        public DynamicLinkedContainerIterator(Node<E> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            if (modCount > expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return position < expectedModCount;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> result = this.list;
            for (int i = 0; i < position; i++) {
                result = result.next;
            }
            position++;
            return result.data;
        }
    }

    /**
     * Класс для хранения данных.
     */
    private static class Node<E> {
        Node<E> next;
        E data;

        public Node(E data) {
            this.data = data;
        }
    }





}
