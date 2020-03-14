package ru.job4j.bomberman2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Board.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Board implements Field {

    /**
     * The playing field.
     */
    private final Lock[][] board;

    public Board(int length, int height) {
        this.board = new Lock[height][length];
        IntStream.range(0, height)
                .forEach(i -> IntStream.range(0, length).forEach(j -> board[i][j] = new ReentrantLock()));
    }

    /**
     * The method tries to move the hero from one cell to another.
     * @param source the source cell.
     * @param target the source cell.
     * @return true - the hero successfully moved from one cell to another,
     * false - the hero didn't move from one cell to another.
     */
    @Override
    public boolean move(ICell source, ICell target) {
        boolean result = false;
        try {
            result = this.board[target.getX()][target.getY()].tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (result) {
            this.board[source.getX()][source.getY()].unlock();
        }
        return result;
    }

    /**
     * The method locks start cell.
     * @param cell the start cell.
     * @return true - the cell is locked,
     * false - the cell isn't locked.
     */
    @Override
    public boolean lockStart(ICell cell) {
        this.board[cell.getX()][cell.getY()].lock();
        return ((ReentrantLock) this.board[cell.getX()][cell.getY()]).isLocked();
    }

    /**
     * The method returns the board length.
     * @return the board length.
     */
    @Override
    public int getLength() {
        return this.board[0].length;
    }

    /**
     * The method returns the board height.
     * @return the board height.
     */
    @Override
    public int getHeight() {
        return this.board.length;
    }
}