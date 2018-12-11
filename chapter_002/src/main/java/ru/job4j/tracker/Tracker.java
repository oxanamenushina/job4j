package ru.job4j.tracker;

import java.util.Arrays;

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
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items[i].getId())) {
                this.items[i] = item;
                item.setId(id);
                break;
            }
        }
    }

    /**
     * Метод находит заявку по идентификатору в массиве и удаляет ее.
     * @param id Идентификатор удаляемой заявки.
     */
    public void delete(String id) {
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items[i].getId())) {
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                this.position--;
                break;
            }
        }
    }

    /**
     * Метод возвращает копию массива без null.
     * @return массив без null.
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Метод поиска заявок с совпадающим именем.
     * @param key Имя искомых заявок.
     * @return Массив заявок с совпадающим именем.
     */
    public Item[] findByName(String key) {
        Item[] copies = new Item[100];
        int j = 0;
        for (int i = 0; i < this.position; i++) {
            if (key.equals(this.items[i].getName())) {
                copies[j++] = this.items[i];
            }
        }
        return Arrays.copyOf(copies, j);
    }

    /**
     * Метод поиска заявки по идентификатору.
     * @param id Идентификатор искомой заявки.
     * @return Найденный Item или null.
     */
    public Item findById(String id) {
        Item found = null;
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items[i].getId())) {
                found = this.items[i];
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