package ru.job4j.search;

import java.util.LinkedList;

/**
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.size() != 0 && tasks.getLast().getPriority() > task.getPriority()) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getPriority() > task.getPriority()) {
                    tasks.add(i, task);
                    break;
                }
            }
        } else {
            tasks.addLast(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}