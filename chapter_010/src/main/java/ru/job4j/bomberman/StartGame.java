package ru.job4j.bomberman;

import java.util.HashSet;
import java.util.Set;

/**
 * StartGame.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StartGame {

    public static void main(String[] args) {
        Set<ICell> startPos = new HashSet<>();
        startPos.add(new Cell(1, 1));
        startPos.add(new Cell(0, 0));
        Game bomberman = new Bomberman(5, 5, startPos);
        bomberman.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        bomberman.finish();
    }
}