package ru.job4j.bomberman2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

 /**
 * BoardTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    @Test
    public void whenLockStartCellThenTrue() throws InterruptedException {
        ICell startPos = new Cell(0, 0);
        Field board = new Board(3, 3);
        List<Boolean> result = new ArrayList<>();
        Thread t = new Thread(() -> result.add(board.lockStart(startPos)));
        t.start();
        t.join();
        Assert.assertThat(result.get(0), is(true));
    }

    @Test
    public void whenMoveToLockedCellThenFalse() {
        ICell startPos1 = new Cell(0, 0);
        ICell startPos2 = new Cell(0, 1);
        Field board = new Board(3, 3);
        List<Boolean> result = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            board.lockStart(startPos1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread t2 = new Thread(() -> {
            board.lockStart(startPos2);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            result.add(board.move(startPos2, startPos1));
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertThat(result.get(0), is(false));
    }

    @Test
    public void whenMoveToEmptyCellThenTrue() throws InterruptedException {
        ICell startPos = new Cell(0, 0);
        Field board = new Board(3, 3);
        List<Boolean> result = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            board.lockStart(startPos);
            result.add(board.move(startPos, new Cell(1, 0)));
        });
        t1.start();
        t1.join();
        Assert.assertThat(result.get(0), is(true));
    }
}