package ru.job4j.bomberman2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * BombermanGame.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class BombermanGame implements Game {

    /**
     * Threads.
     */
    private final Set<Thread> threads;

    /**
     * User input.
     */
    private final Input<ICell> input;

    /**
     * Bomberman.
     */
    private final Hero bomberman;

    public BombermanGame(int length, int height, Input<ICell> input) {
        this.input = input;
        Field board = new Board(length, height);
        Set<ICell> heroesCells = this.generate(length, height, 1, new HashSet<>());
        this.bomberman = new Bomberman(board, heroesCells.iterator().next());
        int monsters = this.getCount(length, height);
        int barriers = this.getCount(length, height);
        this.threads = this.init(board, monsters, barriers, heroesCells);
    }

    public BombermanGame(int length, int height, int monsters, int barriers, Input<ICell> input) {
        this.input = input;
        Field board = new Board(length, height);
        Set<ICell> heroesCells = this.generate(length, height, 1, new HashSet<>());
        this.bomberman = new Bomberman(board, heroesCells.iterator().next());
        int max = (int) (length * height * 0.25);
        int countM = monsters > max ? max : monsters < 1 ? 1 : monsters;
        int countL = barriers > max ? max : monsters < 1 ? 1 : barriers;
        this.threads = this.init(board, countM, countL, heroesCells);
    }

    /**
     * The method initializes threads.
     * @param board the board.
     * @param monsters the number of monsters.
     * @param barriers the numbers of locks.
     * @param heroesCells the cells occupied by heroes.
     * @return threads.
     */
    private Set<Thread> init(Field board, int monsters, int barriers, Set<ICell> heroesCells) {
        Set<Runnable> runnable = new HashSet<>();
        runnable.add(this.bomberman);
        Set<ICell> monstersCells = this.generate(board.getLength(), board.getHeight(), monsters, heroesCells);
        monstersCells.forEach(cell -> runnable.add(new Monster(board, cell)));
        Set<ICell> cells = new HashSet<>(monstersCells);
        cells.addAll(heroesCells);
        Set<ICell> locksCells = this.generate(board.getLength(), board.getHeight(), barriers, cells);
        locksCells.forEach(cell -> runnable.add(() -> board.lockStart(cell)));
        Set<Thread> thrs = new HashSet<>();
        runnable.forEach(r -> thrs.add(new Thread(r)));
        thrs.add(new Thread(() -> {                                          // The thread imitates user input
            while (!Thread.currentThread().isInterrupted()) {
                this.bomberman.addTask(this.input.getValue());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }));
        return thrs;
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

    /**
     * The method generates a random number.
     * @param length the length of the board.
     * @param height the height of the board.
     * @return random number.
     */
    private int getCount(int length, int height) {
        Random random = new Random();
        return random.nextInt((int) (length * height * 0.25)) + 1;
    }

    /**
     * The method generates random cells.
     * @param length the length of the board.
     * @param height the height of the board.
     * @param count the number of cells.
     * @return random cells.
     */
    private Set<ICell> generate(int length, int height, int count, Set<ICell> occupied) {
        Set<ICell> cells = new HashSet<>();
        Random random = new Random();
        while (cells.size() < count) {
            ICell cell = new Cell(random.nextInt(height), random.nextInt(length));
            if (!occupied.contains(cell)) {
                cells.add(cell);
            }
        }
        return cells;
    }
}