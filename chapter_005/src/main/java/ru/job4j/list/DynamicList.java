package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DynamicList<E> implements Iterable<E> {

    private Object[] container = new Object[5];
    private int modCount = 0;
    private int expectedModCount = 0;

    /**
     * Метод добавляет элемент в динамический список.
     * @param value добавляемый элемент.
     */
    public void add(E value) {
        if (modCount >= container.length) {
            container = Arrays.copyOf(container, modCount + 5);
        }
        container[modCount++] = value;
    }

    /**
     * Метод возвращает элемент диннамического списка с заданным индексом.
     * @param index индекс элемента.
     * @return элемент динамического массива с заданным индексом.
     */
    public E get(int index) {
        return index < modCount ? (E) container[index] : null;
    }

    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException {
        expectedModCount = modCount;
        return new DynamicListIterator((E[]) this.container);
    }

    /**
     * Класс DynamicListIterator.
     */
    private class DynamicListIterator implements Iterator<E> {

        private E[] array;
        private int position = 0;

        public DynamicListIterator(E[] array) {
            this.array = array;
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
            return array[position++];
        }
    }
}