package ru.job4j.set;

import ru.job4j.list.DynamicList;
import java.util.Iterator;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<E> implements Iterable<E> {

    private DynamicList<E> list = new DynamicList<>();

    /**
     * Метод добавляет элемент в коллекцию, если в коллекции нет его дубликата.
     * @param element добавляемый элемент.
     */
    public void add(E element) {
        if (!isExist(element)) {
            this.list.add(element);
        }
    }

    /**
     * Метод проверяет существует ли в коллекции дубликат данного элемента.
     * @param element искомый элемент.
     * @return true - дубликат существует, false - нет.
     */
    private boolean isExist(E element) {
        boolean result = false;
        for (E current : this.list) {
            if ((element != null && element.equals(current)) ^ (element == null && current == null)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }
}
