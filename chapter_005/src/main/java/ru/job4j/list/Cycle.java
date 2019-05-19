package ru.job4j.list;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Cycle<T> {

    /**
     * Метод определяет содержит ли список замыкания (зацикленность).
     * @param first связный список.
     * @return true - список содержит замыкание, false - нет.
     */
    public boolean hasCycle(Node<T> first) {
        Node<T> current = first;
        Node<T> node = first;
        boolean result = false;

        while (node != null && node.next != null) {
            current = current.next;
            node = node.next.next;
            if (current == node) {
                result = true;
                break;
            }
        }
        return result;
    }
}
