package ru.job4j.bomberman2;

import java.util.Random;

/**
 * UserInputSimulator.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserInputSimulator implements Input<ICell> {

    /**
     * The length of the board.
     */
    private final int length;

    /**
     * The height of the board.
     */
    private final int height;

    public UserInputSimulator(int length, int height) {
        this.length = length;
        this.height = height;
    }

    /**
     * The method returns random cell.
     * @return random cell.
     */
    @Override
    public ICell getValue() {
        Random random = new Random();
        return new Cell(random.nextInt(height), random.nextInt(length));
    }
}