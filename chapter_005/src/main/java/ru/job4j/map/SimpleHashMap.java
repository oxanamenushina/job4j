package ru.job4j.map;

import java.util.*;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable {
    private Pair<K, V>[] table = (Pair<K, V>[]) new Pair[4];
    private int counter = 0;
    private int modCount = 0;

    /**
     * Метод добавляет пару "ключ-значение" в массив.
     * @param key ключ.
     * @param value значение.
     * @return true - пара "ключ-значение" добавлена в массив, false - нет.
     */
    public boolean insert(K key, V value) {
        if (this.counter == this.table.length) {
            expand();
        }
        boolean result = false;
        int index = getIndex(key, this.table.length);
        if (this.table[index] == null || (this.table[index] != null && this.table[index].getKey().equals(key))) {
            this.table[index] = new Pair<>(key, value);
            this.counter++;
            this.modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает значение элемента по его ключу.
     * @param key ключ.
     * @return значение элемента или null.
     */
    public V get(K key) {
        int index = getIndex(key, this.table.length);
        return this.table[index] != null && this.table[index].getKey().equals(key) ? this.table[index].getValue() : null;
    }

    /**
     * Метод удаляет элемент из массива по ключу.
     * @param key ключ.
     * @return true - элемент удален, false - нет.
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = getIndex(key, this.table.length);
        if (this.table[index].getKey().equals(key)) {
            this.table[index] = null;
            result = true;
            this.counter--;
            this.modCount++;
        }
        return result;
    }

    /**
     * Метод вичисляет и возвращает индекс массива по хеш-коду ключа и длине массива.
     * @param key ключ.
     * @param length длина массива.
     * @return индекс массива.
     */
    private int getIndex(K key, int length) {
        return (key.hashCode() ^ (key.hashCode() >>> 16)) & (length - 1);
    }

    /**
     * Метод увеличивает длину массива и перераспределяет элементы с учетом их нового hash.
     */
    private void expand() {
        counter = 0;
        Pair<K, V>[] newArr = (Pair<K, V>[]) new Pair[table.length * 2];
        for (Pair<K, V> pair : this.table) {
            int index = getIndex(pair.getKey(), newArr.length);
            if (newArr[index] == null) {
                newArr[index] = pair;
                this.counter++;
            }
        }
        this.modCount++;
        this.table = newArr;
    }

    /**
     * Метод возвращает таблицу пар "ключ-значение" без null.
     * @return таблица пар "ключ-значение" без null.
     */
    public Pair<K, V>[] getTable() {
        return deleteNull(this.table);
    }

    /**
     * Метод преобразует таблицу с null в таблицу без null.
     * @param array таблица пар "ключ-значение".
     * @return таблица без null.
     */
    private Pair<K, V>[] deleteNull(Pair<K, V>[] array) {
        Pair<K, V>[] arr = (Pair<K, V>[]) new Pair[array.length];
        int ind = 0;
        for (Pair<K, V> pair : array) {
            if (pair != null) {
                arr[ind++] = pair;
            }
        }
        return Arrays.copyOf(arr, ind);
    }

    @Override
    public Iterator<Pair<K, V>> iterator() throws ConcurrentModificationException {
        return new SimpleHashMap<K, V>.TableIterator(this.table);
    }

    /**
     * Класс PairIterator.
     */
    private class TableIterator implements Iterator<Pair<K, V>> {
        private int expectedModCount = modCount;
        private int position = 0;
        private Pair<K, V>[] arr;

        public TableIterator(Pair<K, V>[] array) {
            this.arr = deleteNull(array);
        }

        @Override
        public boolean hasNext() {
            if (modCount > expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return position < arr.length;
        }

        @Override
        public Pair<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arr[position++];
        }
    }

    /**
     * Класс Pair.
     */
    public class Pair<E, T> {
        private E key;
        private T value;

        public Pair(E key, T value) {
            this.key = key;
            this.value = value;
        }

        public E getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
