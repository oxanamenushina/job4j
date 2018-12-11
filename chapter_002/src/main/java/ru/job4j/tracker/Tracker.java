package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
/**
 * Item.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод заменяет заявку с заданным идентификатором в массиве.
     * @param id Идентификатор заменяемой заявки.
     * @param item Новая заявка.
     */
    public void replace(String id, Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if (id.equals(this.items[i].getId())) {
                this.items[i] = item;
                break;
            }
        }
    }

    /**
     * Метод находит заявку по идентификатору в массиве и удаляет ее.
     * @param id Идентификатор удаляемой заявки.
     */
    public void delete(String id) {
        for (int i = 0; i < this.items.length; i++) {
            if (id.equals(this.items[i].getId())) {
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                break;
            }
        }
    }

    /**
     * Метод возвращает копию массива без null.
     * @return массив без null.
     */
    public Item[] findAll() {
        int ind = 0;
        while (ind < items.length && this.items[ind] != null) {
            ind++;
        }
        Item[] result = new Item[ind];
        System.arraycopy(this.items, 0, result, 0, result.length);
        return result;
    }

    /**
     * Метод поиска заявок с совпадающим именем.
     * @param key Имя искомых заявок.
     * @return Массив заявок с совпадающим именем.
     */
    public Item[] findByName(String key) {
        List<Item> copies = new ArrayList<>();
        for (Item item : this.items) {
            if (item != null && key.equals(item.getName())) {
                copies.add(item);
            }
        }
        return copies.toArray(new Item[copies.size()]);
    }

    /**
     * Метод поиска заявки по идентификатору.
     * @param id Идентификатор искомой заявки.
     * @return Найденный Item или null.
     */
    public Item findById(String id) {
        Item found = null;
        for (Item item : this.items) {
            if (id.equals(item.getId())) {
                found = item;
                break;
            }
        }
        return found;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return Integer.toString(position) + System.currentTimeMillis();
    }
}