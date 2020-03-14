package ru.job4j.bomberman2;

/**
 * StartGame.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StartGame {

    public static void main(String[] args) {
        Input<ICell> input = new UserInputSimulator(5, 5);
        Game bomberman = new BombermanGame(5, 5, input);
        bomberman.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        bomberman.finish();
    }
}