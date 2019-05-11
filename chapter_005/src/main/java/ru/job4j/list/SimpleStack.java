package ru.job4j.list;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<T> {
    SimpleArrayList<T> list = new SimpleArrayList<>();

    /**
     * Метод помещает значение в коллекцию.
     * @param value значение, помещаемое в коллекцию.
     */
    public void push(T value) {
        list.add(value);
    }

    /**
     * Метод возвращает значение и удалять его из коллекции.
     * @return удаляемое значение.
     */
    public T poll() {
        return list.delete();
    }
}
