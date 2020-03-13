package ru.job4j.bomberman;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Hero.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Hero implements Player {

    /**
     * The playing field.
     */
    private final Field board;

    /**
     * The last passed cells.
     */
    private final BlockingDeque<ICell> passed;

    public Hero(Field board, ICell startPos) {
        this.board = board;
        this.passed = new LinkedBlockingDeque<>(Set.of(startPos));
    }

    /**
     * The method runs the hero's moves.
     */
    @Override
    public void run() {
        this.board.lockStart(this.passed.peekLast());
        long startTime = System.currentTimeMillis();
        while (!Thread.currentThread().isInterrupted()) {
            if (this.executeAction()) {
                long sleepTime = 1000 - (System.currentTimeMillis() - startTime);
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                startTime += 1000;
            }
        }
    }

    /**
     * The method moves the hero to one of the neighboring cells.
     * @return true - the hero successfully moved to one of the neighboring cells,
     * false - the hero didn't move to one of the neighboring cells.
     */
    private boolean executeAction() {
        ICell current = this.passed.peekLast();
        ICell previous = this.passed.size() > 1 ? this.passed.pollFirst() : null;
        Set<ICell> neighbors = this.getNeighbours(current);
        neighbors.remove(previous);
        Queue<ICell> cells = new LinkedList<>(neighbors);
        if (this.passed.size() > 1) {
            cells.offer(previous);
        }
        boolean result = false;
        while (!cells.isEmpty()) {
            ICell next = cells.poll();
            if (this.board.move(current, next)) {
                result = true;
                this.passed.offerLast(next);
                break;
            }
        }
        return result;
    }

    /**
     * The method searches for neighboring cells vertically and horizontally.
     * @param current current cell.
     * @return neighboring cells.
     */
    private Set<ICell> getNeighbours(ICell current) {
        Set<ICell> neighbours = new HashSet<>();
        for (int i = -1; i <= 1; i += 2) {
            if (current.getX() + i < this.board.getLength() && current.getX() + i >= 0) {
                neighbours.add(new Cell(current.getX() + i, current.getY()));
            }
            if (current.getY() + i < this.board.getHeight() && current.getY() + i >= 0) {
                neighbours.add(new Cell(current.getX(), current.getY() + i));
            }
        }
        return neighbours;
    }
}