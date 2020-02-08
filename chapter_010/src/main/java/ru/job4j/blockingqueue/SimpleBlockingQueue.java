package ru.job4j.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SimpleBlockingQueue.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * The queue.
     */
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    /**
     * The method adds the value to the queue.
     * @param value the value to add.
     */
    public synchronized void offer(T value) {
        while (!this.queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.queue.offer(value);
        System.out.println("Producer: " + value.toString());
        notifyAll();
    }

    /**
     * The method returns and deletes
     * the value from the the queue.
     * @return value.
     */
    public synchronized T poll() {
        while (this.queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T value = this.queue.poll();
        System.out.println("Consumer: " + value.toString());
        notifyAll();
        return value;
    }

    /**
     * The method checks if the queue is empty.
     * @return true - the queue is empty,
     * false - the queue isn't empty.
     */
    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}