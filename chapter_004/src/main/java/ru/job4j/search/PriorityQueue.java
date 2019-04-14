package ru.job4j.search;

import java.util.LinkedList;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueue {
    public LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяется по полю "приоритет".
     * @param task задача
     */
    public void put(Task task) {
        if (this.tasks.size() != 0 && this.tasks.getLast().getPriority() > task.getPriority()) {
            this.tasks.add((int) this.tasks.stream().filter((t) -> t.getPriority() < task.getPriority()).count(), task);
        } else {
            tasks.addLast(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}