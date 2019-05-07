package ru.job4j.generic;

/**
 * @version $Id$
 * @since 0.1
 */
public interface Store<T extends Base> {

    /**
     * Метод добавляет указанный элемент (model) в хранилище.
     * @param model добавляемый элемент.
     */
    void add(T model);

    /**
     * Метод заменяет указанным элементом (model) элемент с идентификатором id.
     * @param id идентификатор заменяемого элемента.
     * @param model новый элемент.
     * @return true - элемент заменен, false - нет.
     */
    boolean replace(String id, T model);

    /**
     * Метод удаляет из хранилища элемент с идентификатором id.
     * @param id идентификатор удаляемого элемента.
     * @return true - элемент удален, false - нет.
     */
    boolean delete(String id);

    /**
     * Метод возвращает элемент с заданным id.
     * @param id идентификатор искомого элемента.
     * @return элемент с данным id.
     */
    T findById(String id);
}