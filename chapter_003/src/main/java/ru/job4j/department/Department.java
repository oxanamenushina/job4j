package ru.job4j.department;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Department {
    /**
     * Метод сортирует массив кодов подразделений по возрастанию.
     * @param codes массив кодов подразделений
     * @return упорядоченный по возрастанию массив кодов подразделений
     */
    public String[] ascendingSort(String[] codes) {
        Arrays.sort(codes, new Comparator<String>() {
            public int compare(String subdivision1, String subdivision2) {
                int result = 0;
                String[] codes1 = subdivision1.split("\\\\");
                String[] codes2 = subdivision2.split("\\\\");
                for (int i = 0; i < (codes1.length > codes2.length ? codes2.length : codes1.length); i++) {
                    result = codes1[i].length() == codes2[i].length()
                            ? codes1[i].compareTo(codes2[i]) : codes1[i].length() - codes2[i].length();
                    if (result != 0) {
                        break;
                    }
                }
                return result != 0 ? result : codes1.length != codes2.length ? codes1.length - codes2.length : 0;
            }
        });
        return codes;
    }

    /**
     * Метод сортирует массив кодов подразделений по убыванию.
     * @param codes массив кодов подразделений
     * @return упорядоченный по убыванию массив кодов подразделений
     */
    public String[] descendingSort(String[] codes) {
        Arrays.sort(codes, new Comparator<String>() {
            public int compare(String subdivision1, String subdivision2) {
                int result = 0;
                String[] codes1 = subdivision1.split("\\\\");
                String[] codes2 = subdivision2.split("\\\\");
                for (int i = 0; i < (codes1.length > codes2.length ? codes2.length : codes1.length); i++) {
                    result = codes1[i].length() == codes2[i].length()
                            ? codes2[i].compareTo(codes1[i]) : codes2[i].length() - codes1[i].length();
                    if (result != 0) {
                        break;
                    }
                }
                return result != 0 ? result : codes1.length != codes2.length ? codes1.length - codes2.length : 0;
            }
        });
        return codes;
    }
}
