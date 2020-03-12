package ru.job4j.pool;

import ru.job4j.blockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * ThreadPool.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ThreadPool {

    /**
     * The list of threads.
     */
    private final List<Thread> threads = new LinkedList<>();

    /**
     * The task queue.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * The indicator that shows if the thread pool is working.
     */
    private volatile boolean isWork;

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        IntStream.range(0, size).forEach(t -> {
            Thr current = new Thr();
            this.threads.add(current);
            current.start();
        });
        this.isWork = true;
    }

    /**
     * The method adds tasks to the blocking queue.
     * @param job the task to complete.
     */
    public void work(Runnable job) {
        if (this.isWork && job != null) {
            this.tasks.offer(job);
        }
    }

    /**
     * The method closes the thread pool.
     */
    public void shutdown() {
        this.isWork = false;
        this.threads.forEach(Thread::interrupt);
        for (Thread thread : this.threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public class Thr extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                tasks.poll().run();
            }
        }
    }
}