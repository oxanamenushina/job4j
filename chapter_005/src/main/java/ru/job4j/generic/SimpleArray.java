package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {

    private T[] models;
    private int position = 0;

    /**
     * Конструктор.
     * @param size размер массива.
     */
    public SimpleArray(int size) {
        this.models = (T[]) new Object[size];
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку.
     * @param model добавляемый элемент.
     */
    public void add(T model) throws ArrayOverflowException {
        if (this.position >= models.length) {
            throw new ArrayOverflowException();
        }
        models[this.position++] = model;
    }

    /**
     * Метод заменяет указанным элементом (model) элемент, находящийся по индексу index.
     * @param index индекс заменяемого элемента.
     * @param model новый элемент.
     */
    public boolean set(int index, T model) {
        boolean result = false;
        if (index < this.position) {
            this.models[index] = model;
            result = true;
        }
        return result;
    }

    /**
     * Метод удаляет элемент по указанному индексу.
     * @param index индекс удаляемого элемента.
     */
    public void remove(int index) {
        System.arraycopy(this.models, index + 1, this.models, index, this.models.length - index - 1);
        this.position--;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     * @param index индекс возвращаемого элемента.
     */
    public T get(int index) {
        return models[index];
    }

    @Override
    public Iterator<T> iterator() {
        class IteratorSimpleArray implements Iterator<T> {
            private T[] array;
            private int ind = 0;

            public IteratorSimpleArray(T[] array) {
                this.array = array;
            }

            @Override
            public boolean hasNext() {
                return ind < array.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[ind++];
            }
        }
        return new IteratorSimpleArray(this.models);
    }
}
