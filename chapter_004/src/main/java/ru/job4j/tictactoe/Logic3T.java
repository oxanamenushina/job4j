package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Метод проверяет, есть ли в поле выигрышные комбинации для Крестика.
     * @return true - выигрышные комбинации для Крестика есть, false - нет.
     */
    public boolean isWinnerX() {
        return IntStream.range(0, table.length).anyMatch(i -> (table[0][i].hasMarkX() && isWin(i, 0, Figure3T::hasMarkX))
                || (table[i][0].hasMarkX() && isWin(i, 1, Figure3T::hasMarkX)));
    }

    /**
     * Метод проверяет, есть ли в поле выигрышные комбинации для Нолика.
     * @return true - выигрышные комбинации для Нолика есть, false - нет.
     */
    public boolean isWinnerO() {
        return IntStream.range(0, table.length).anyMatch(i -> (table[0][i].hasMarkO() && isWin(i, 0, Figure3T::hasMarkO))
                || (table[i][0].hasMarkO() && isWin(i, 1, Figure3T::hasMarkO)));
    }

    /**
     * Метод проверяет, есть ли пустые клетки для новых ходов.
     * @return true - выигрышные комбинации для Нолика есть, false - нет.
     */
    public boolean hasGap() {
        return Arrays.stream(table).flatMap(Arrays::stream).anyMatch(figure -> !figure.hasMarkO() && !figure.hasMarkX());
    }

    /**
     * Метод проверяет наличие выигрышных комбинаций.
     * @param coord координата.
     * @param sign указывает ось: 0 - X, 1 - Y.
     * @param mark ппедикат для выбора проверки Крестиков или Ноликов.
     * @return true - есть выигрышная комбинация, false - нет.
     */
    private boolean isWin(int coord, int sign, Predicate<Figure3T> mark) {
        int normal = 0;
        int diagonal = 0;
        for (int i = 0; i < table.length; i++) {
            normal = mark.test(table[sign == 0 ? i : coord][sign == 1 ? i : coord]) ? ++normal : normal;
            diagonal = coord == 0 && mark.test(table[i][i])
                    || coord == table.length - 1 && mark.test(table[i][table.length - 1 - i]) ? ++diagonal : diagonal;
        }
        return diagonal == table.length ^ normal == table.length;
    }
}