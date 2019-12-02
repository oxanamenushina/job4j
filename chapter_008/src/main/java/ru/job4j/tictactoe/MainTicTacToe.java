package ru.job4j.tictactoe;

import java.util.List;

/**
 * MainTicTacToe.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MainTicTacToe implements Game {

    /**
     * The Input object.
     */
    private Input input;

    /**
     * The list of questions.
     */
    private List<String> questions;

    /**
     * The Display object.
     */
    private Display display;

    /**
     * The logic of the game.
     */
    private Logic logic;

    public MainTicTacToe(Input input, Display display, Logic logic, Menu menu) {
        this.input = input;
        this.display = display;
        this.logic = logic;
        this.questions = menu.getMenu();
    }

    /**
     * The method starts the main loop.
     */
    @Override
    public void play() {
        String answer = this.input.ask(this.questions.get(0) + this.questions.get(1), 1);
        while (!answer.equalsIgnoreCase("e")) {
            answer = this.input.ask(this.questions.get(2), 2);
            int size = answer.length() > 0 ? Integer.parseInt(answer) : 3;
            boolean isCompX = this.input.ask(this.questions.get(3), 3).equalsIgnoreCase("0");
            this.logic.init(size, isCompX);
            this.makeMoves();
            String result = this.logic.isUserWin() ? this.questions.get(5) : this.logic.isCompWin()
                    ? this.questions.get(6) : this.questions.get(7);
            answer = this.input.ask(new StringBuilder()
                    .append(this.display.display()).append(result).append(this.questions.get(1)).toString(), 1);
            this.display.clear();
        }
    }

    /**
     * The cycle of user and computer moves.
     */
    private void makeMoves() {
        boolean result = true;
        while (result) {
            String answer = this.input.ask(this.display.display() + this.questions.get(4), 4);
            String[] strs = answer.replace(" ", "").split(",");
            result = this.logic.move(Integer.parseInt(strs[0]) - 1, Integer.parseInt(strs[1]) - 1);
        }
    }
}