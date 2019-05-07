package ru.job4j.generic;

import java.util.stream.IntStream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store {

    private SimpleArray<Base> store;
    private int count = 0;

    /**
     * Конструктор.
     * @param store объект типа SimpleArray.
     */
    public AbstractStore(SimpleArray<Base> store) {
        this.store = store;
    }

    @Override
    public void add(Base model) {
        this.store.add(model);
        count++;
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            store.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            store.remove(index);
            count--;
            result = true;
        }
        return result;
    }

    @Override
    public Base findById(String id) {
        int index = findIndexById(id);
        return index != -1 ? store.get(index) : null;
    }

    /**
     * Метод возвращает индекс элемента с заданным id.
     * @param id идентификатор искомого элемента.
     * @return индекс.
     */
    private int findIndexById(String id) {
        return IntStream.range(0, count).filter(n -> store.get(n).getId().equals(id)).findFirst().orElse(-1);
    }
}
