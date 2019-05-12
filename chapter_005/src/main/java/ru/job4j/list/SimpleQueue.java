package ru.job4j.list;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<T> {

    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();
    private int counter = 0;

    /**
     * Метод помещает значение в коллекцию.
     * @param value значение, помещаемое в коллекцию.
     */
    public void push(T value) {
        this.in.push(value);
        this.counter++;
    }

    /**
     * Метод возвращает значение и удалять его из коллекции.
     * @return удаляемое значение.
     */
    public T poll() {
        this.reverse();
        return this.out.poll();
    }

    /**
     * Метод перемещает данные из одного стека в другой.
     */
    private void reverse() {
        for (int i = 0; i < this.counter; i++) {
            this.out.push(this.in.poll());
        }
        this.counter = 0;
    }
}
