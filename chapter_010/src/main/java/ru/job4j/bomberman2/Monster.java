package ru.job4j.bomberman2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Monster.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Monster implements Player {

    /**
     * The playing field.
     */
    private final Field board;

    /**
     * The last passed cells.
     */
    private final BlockingDeque<ICell> passed;

    public Monster(Field board, ICell startPos) {
        this.board = board;
        this.passed = new LinkedBlockingDeque<>(Set.of(startPos));
    }

    /**
     * The method runs the monster's moves.
     */
    @Override
    public void run() {
        this.board.lockStart(this.passed.peekLast());
        while (!Thread.currentThread().isInterrupted()) {
            this.executeAction();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * The method moves the monster to one of the neighboring cells.
     */
    private void executeAction() {
        ICell current = this.passed.peekLast();
        ICell previous = this.passed.size() > 1 ? this.passed.pollFirst() : null;
        Set<ICell> neighbors = this.getNeighbours(current);
        neighbors.remove(previous);
        Queue<ICell> cells = new LinkedList<>(neighbors);
        if (this.passed.size() > 1) {
            cells.offer(previous);
        }
        while (!cells.isEmpty()) {
            ICell next = cells.poll();
            if (this.board.move(current, next)) {
                this.passed.offerLast(next);
                break;
            }
        }
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
