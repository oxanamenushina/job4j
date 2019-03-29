package ru.job4j.department;

import java.util.*;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Department {
    /**
     * Метод добавляет пропущенные подразделения.
     * @param subdivisions массив кодов подразделений
     * @return массив кодов подразделений c добавленными строками с кодом верхнеуровневых подразделений
     */
    public String[] addMissed(String[] subdivisions) {
        Set<String> codes = new HashSet<>(Arrays.asList(subdivisions));
        for (String subdiv : subdivisions) {
            String code = subdiv;
            while (code.contains("\\")) {
                code = code.substring(0, code.lastIndexOf("\\"));
                codes.add(code);
            }
        }
        return codes.toArray(new String[0]);
    }

    /**
     * Метод добавляет пропущенные подразделения.
     * @param subdivisions массив кодов подразделений
     * @return Map, ключами которой являются коды подразделений, значениями - массивы их составных частей
     */
    private Map<String, String[]> codesToMap(String[] subdivisions) {
        Map<String, String[]> codes = new HashMap<>();
        for (String subdiv : this.addMissed(subdivisions)) {
            codes.put(subdiv, subdiv.split("\\\\"));
        }
        return codes;
    }

    /**
     * Метод сортирует массив кодов подразделений по возрастанию.
     * @param subdivisions массив кодов подразделений
     * @return упорядоченный по возрастанию массив кодов подразделений
     */
    public String[] ascendingSort(String[] subdivisions) {
        String[] total = this.addMissed(subdivisions);
        Arrays.sort(total, new Comparator<String>() {
            public int compare(String subdiv1, String subdiv2) {
                int result = 0;
                String[] codes1 = codesToMap(subdivisions).get(subdiv1);
                String[] codes2 = codesToMap(subdivisions).get(subdiv2);
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
        return total;
    }

    /**
     * Метод сортирует массив кодов подразделений по убыванию.
     * @param subdivisions массив кодов подразделений
     * @return упорядоченный по убыванию массив кодов подразделений
     */
    public String[] descendingSort(String[] subdivisions) {
        String[] total = this.addMissed(subdivisions);
        Arrays.sort(total, new Comparator<String>() {
            public int compare(String subdiv1, String subdiv2) {
                int result = 0;
                String[] codes1 = codesToMap(subdivisions).get(subdiv1);
                String[] codes2 = codesToMap(subdivisions).get(subdiv2);
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
        return total;
    }
}