package ru.job4j.tictactoe;

/**
 * StartTicTacToe.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StartTicTacToe {

    public static void main(String[] args) {
        CellFactory factory = new CellTTTFactory();
        Field field = new PlayingField(factory);
        Input input = new ValidateInput(new ConsoleUI(new CheckInput(field)));
        Display display = new DisplayTicTacToe();
        Logic logic = new GameLogic(field, display);
        Menu menu = new GameMenu();
        Game game = new MainTicTacToe(input, display, logic, menu);
        game.play();
    }
}