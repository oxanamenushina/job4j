package ru.job4j.department;

import java.util.*;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Department {
    /**
     * Метод разбивает строки кодов подразделений на составные части.
     * @param subdiv код подразделения
     * @return массив составных кодов подразделений
     */
    public String[] codeSplit(String subdiv) {
        List<String> codes = new ArrayList<>();
        codes.add(subdiv);
        while (subdiv.contains("\\")) {
            subdiv = subdiv.substring(0, subdiv.lastIndexOf("\\"));
            codes.add(subdiv);
        }
        return codes.toArray(new String[0]);
    }

    /**
     * Метод добавляет пропущенные подразделения.
     * @param subdivisions массив кодов подразделений
     * @return массив кодов подразделений, дополненный пропущенными подразделениями
     */
    public String[] addMissed(String[] subdivisions) {
        return Arrays.stream(subdivisions).flatMap((subdiv) -> Arrays.stream(codeSplit(subdiv)))
                .distinct().toArray(String[]::new);
    }

    /**
     * Метод сортирует массив кодов подразделений по возрастанию.
     * @param subdivisions массив кодов подразделений
     * @return упорядоченный по возрастанию массив кодов подразделений
     */
    public String[] ascendingSort(String[] subdivisions) {
        return Arrays.stream(addMissed(subdivisions)).map((code) -> code.split("\\\\"))
                .sorted((subdiv1, subdiv2) -> new AscendingComparator().compare(subdiv1, subdiv2))
                .map((codes) -> (String.join("\\", Arrays.asList(codes)))).toArray(String[]::new);
    }

    /**
     * Метод сортирует массив кодов подразделений по убыванию.
     * @param subdivisions массив кодов подразделений
     * @return упорядоченный по убыванию массив кодов подразделений
     */
    public String[] descendingSort(String[] subdivisions) {
        return Arrays.stream(addMissed(subdivisions)).map((code) -> code.split("\\\\"))
                .sorted((subdiv1, subdiv2) -> new DescendingComparator().compare(subdiv1, subdiv2))
                .map((codes) -> (String.join("\\", Arrays.asList(codes)))).toArray(String[]::new);
    }
}