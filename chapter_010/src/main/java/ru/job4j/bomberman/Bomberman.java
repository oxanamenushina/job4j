package ru.job4j.bomberman;

import java.util.HashSet;
import java.util.Set;

/**
 * Bomberman.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Bomberman implements Game {

    /**
     * Threads that imitate the behavior of heroes.
     */
    private final Set<Thread> threads = new HashSet<>();

    public Bomberman(int length, int height, Set<ICell> locked) {
        Field board = new Board(length, height);
        Set<Player> heroes = new HashSet<>();
        if (locked != null) {
            locked.forEach(cell -> heroes.add(new Hero(board, cell)));
        }
        heroes.forEach(h -> this.threads.add(new Thread(h)));
    }

    /**
     * The method starts all threads.
     */
    @Override
    public void start() {
        this.threads.forEach(Thread::start);
    }

    /**
     * The method interrupts all threads.
     */
    @Override
    public void finish() {
        this.threads.forEach(Thread::interrupt);
        for (Thread thread : this.threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}