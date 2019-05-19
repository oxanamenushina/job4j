package ru.job4j.list;

/**
 * Класс для хранения данных.
 */
public class Node<T> {
    private T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
