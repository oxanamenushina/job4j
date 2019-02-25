package ru.job4j.coffee;

import java.util.Arrays;

/**
 * Coffe.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Coffee {
    /**
     * Метод реализует выдачу сдачи минимальным количеством монет.
     *
     * @param value сумма на входе.
     * @param price цена кофе.
     * @return массив монет сдачи.
     */
    public int[] changes(int value, int price) {
        int[] change = new int[100];
        int[] coins = {10, 5, 2, 1};
        int balance = value;
        int i = 0;
        int k = 0;
        while (balance > price) {
            if (balance - price >= coins[i]) {
                int count = (balance - price) / coins[i];
                for (int j = k; j < k + count; j++) {
                    change[j] = coins[i];
                }
                balance -= coins[i] * count;
                k += count;
            }
            i++;
        }
        return Arrays.copyOf(change, k);
    }
}
