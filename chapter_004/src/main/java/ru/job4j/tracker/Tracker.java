package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Tracker.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище.
     * @param item новая заявка.
     * @return заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(this.position++, item);
        return item;
    }

    /**
     * Метод заменяет заявку с заданным идентификатором в массиве.
     * @param id Идентификатор заменяемой заявки.
     * @param item Новая заявка.
     * @return true - заявка заменена, false - нет.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items.get(i).getId())) {
                this.items.set(i, item);
                item.setId(id);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод находит заявку по идентификатору в массиве и удаляет ее.
     * @param id Идентификатор удаляемой заявки.
     * @return true - заявка удалена, false - нет.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items.get(i).getId())) {
                this.items.remove(i);
                this.position--;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает копию массива без null.
     * @return массив без null.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод поиска заявок с совпадающим именем.
     * @param key Имя искомых заявок.
     * @return Массив заявок с совпадающим именем.
     */
    public List<Item> findByName(String key) {
        List<Item> copies = new ArrayList<>();
        for (int i = 0; i < this.position; i++) {
            if (key.equals(this.items.get(i).getName())) {
                copies.add(this.items.get(i));
            }
        }
        return copies;
    }

    /**
     * Метод поиска заявки по идентификатору.
     * @param id Идентификатор искомой заявки.
     * @return Найденный Item или null.
     */
    public Item findById(String id) {
        Item found = null;
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items.get(i).getId())) {
                found = this.items.get(i);
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