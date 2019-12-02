package ru.job4j.tictactoe;

import java.util.Arrays;

/**
 * CheckInput.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CheckInput {

    /**
     * The playing field.
     */
    private Field field;

    public CheckInput(Field field) {
        this.field = field;
    }

    /**
     * The method checks the input.
     * @param answer the input data.
     * @param number the number of the question.
     * @return true - the input is correct,
     * false - the input isn't correct.
     */
    public boolean check(String answer, int number) {
        return number == 1 ? answer.matches("[enEN]{1}")
                : number == 2 ? answer.matches("[3-9]?|[1-9][0-9]{1}")
                : number == 3 ? answer.matches("[xX0]{1}")
                : checkNumbers(answer);
    }

    /**
     * The method checks whether the input data are numbers.
     * @param answer the input data.
     * @return true - the input data are numbers,
     * false - the input data aren't numbers.
     */
    private boolean checkNumbers(String answer) {
        return answer.matches("[1-9]{1}[0-9]*,[ ]*[1-9]{1}[0-9]*") && this.checkCoords(answer);
    }

    /**
     * The method checks the input coordinates.
     * @param answer the input coordinates.
     * @return true - the coordinates are correct.
     * false - the coordinates aren't correct.
     */
    private boolean checkCoords(String answer) {
        String str = answer.replace(" ", "");
        String[] strs = str.split(",");
        int[] nums = new int[]{Integer.parseInt(strs[0]), Integer.parseInt(strs[1])};
        return Arrays.stream(nums).allMatch(n -> n <= this.field.getField().length)
                && this.field.isEmpty(nums[0] - 1, nums[1] - 1);
    }
}