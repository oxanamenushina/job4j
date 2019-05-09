package ru.job4j.list;

/**
 * Класс SimpleArrayList.
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     * @param data данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаляет первый элемент в списке.
     * @return данные удаляемого элемента.
     */
    public E delete() {
        E value = null;
        if (size > 0) {
            value = this.first.data;
            Node<E> del = this.first;
            this.first = first.next;
            del.next = null;
            this.size--;
        }
        return value;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index индекс.
     * @return данные, хранящиеся в элементе с заданным индексом.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     * @return размер коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}