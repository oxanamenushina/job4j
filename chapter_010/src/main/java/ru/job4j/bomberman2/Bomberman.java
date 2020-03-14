package ru.job4j.bomberman2;

import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Bomberman.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Bomberman implements Hero {

    /**
     * The playing field.
     */
    private final Field board;

    /**
     * The user's next steps.
     */
    private final BlockingDeque<ICell> steps;

    public Bomberman(Field board, ICell startPos) {
        this.board = board;
        this.steps = new LinkedBlockingDeque<>(Set.of(startPos));
    }

    /**
     * The method runs the hero's moves.
     */
    @Override
    public void run() {
        this.board.lockStart(this.steps.peekFirst());
        while (!Thread.currentThread().isInterrupted()) {
            if (this.steps.size() > 1) {
                this.executeAction();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * The method moves the hero to one of the neighboring cells.
     */
    private void executeAction() {
        ICell current = this.steps.pollFirst();
        boolean result = false;
        ICell next;
        while (!result && !this.steps.isEmpty()) {
            next = this.steps.pollFirst();
            result = this.checkCell(current, next) && this.board.move(current, next);
            if (result) {
                this.steps.offerFirst(next);
            }
        }
        if (!result) {
            this.steps.offerFirst(current);
        }
    }

    /**
     * The method adds a cell to the step queue.
     * @param cell the cell to add.
     */
    @Override
    public void addTask(ICell cell) {
        if (cell != null) {
            this.steps.offerLast(cell);
        }
    }

    /**
     * The method checks if the next cell is adjacent to the current one.
     * @param current the current cell.
     * @param next the next cell.
     * @return true - the next cell is adjacent to the current one,
     * false - the next cell isn't adjacent to the current one.
     */
    private boolean checkCell(ICell current, ICell next) {
        return ((next.getX() == current.getX() + 1 || next.getX() == current.getX() - 1)
                && next.getY() == current.getY() && next.getX() < this.board.getHeight() && next.getX() >= 0)
                ^ ((next.getY() == current.getY() + 1 || next.getY() == current.getY() - 1)
                && next.getX() == current.getX() && next.getY() < this.board.getLength() && next.getY() >= 0);
    }
}