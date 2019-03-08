package ru.job4j.comparator;

import java.util.Comparator;

/**
 * @version $Id$
 * @since 0.1
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        for (int i = 0; i < (left.length() <= right.length() ? left.length() : right.length()); i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }
        return result != 0 || left.length() == right.length() ? result : Integer.compare(left.length(), right.length());
    }
}

