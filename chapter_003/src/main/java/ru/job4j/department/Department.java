package ru.job4j.department;

import java.util.*;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Department {
    /**
     * Метод разбивает строки кодов подразделений на составные части и добавляет пропущенные подразделения.
     * @param subdivisions массив кодов подразделений
     * @return Map, ключами которой являются массивы составных частей кодов разделений,
     * значениями - коды подразделений, дополненные пропущенными подразделениями
     */
    public HashMap<String[], String> addMissed(String[] subdivisions) {
        HashMap<String[], String> codes = new HashMap<>();
        for (String subdiv : subdivisions) {
            codes.put(subdiv.split("\\\\"), subdiv);
            String code = subdiv;
            while (code.contains("\\")) {
                code = code.substring(0, code.lastIndexOf("\\"));
                codes.put(code.split("\\\\"), code);
            }
        }
        return codes;
    }

    /**
     * Метод сортирует массив кодов подразделений по возрастанию.
     * @param subdivisions массив кодов подразделений
     * @return упорядоченный по возрастанию массив кодов подразделений
     */
    public String[] ascendingSort(String[] subdivisions) {
        TreeMap<String[], String> codes = new TreeMap<>(new AscendingComparator());
        codes.putAll(addMissed(subdivisions));
        return codes.values().toArray(new String[0]);
    }

    /**
     * Метод сортирует массив кодов подразделений по убыванию.
     * @param subdivisions массив кодов подразделений
     * @return упорядоченный по убыванию массив кодов подразделений
     */
    public String[] descendingSort(String[] subdivisions) {
        TreeMap<String[], String> codes = new TreeMap<>(new DescendingComparator());
        codes.putAll(addMissed(subdivisions));
        return codes.values().toArray(new String[0]);
    }
}