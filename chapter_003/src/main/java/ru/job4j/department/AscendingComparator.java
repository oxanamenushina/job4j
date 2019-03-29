package ru.job4j.department;

import java.util.Comparator;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class AscendingComparator implements Comparator<String[]> {
    @Override
    public int compare(String[] subdiv1, String[] subdiv2) {
        int result = 0;
        for (int i = 0; i < (subdiv1.length > subdiv2.length ? subdiv2.length : subdiv1.length); i++) {
            result = subdiv1[i].length() == subdiv2[i].length()
                    ? subdiv1[i].compareTo(subdiv2[i]) : subdiv1[i].length() - subdiv2[i].length();
            if (result != 0) {
                break;
            }
        }
        return result != 0 ? result : subdiv1.length != subdiv2.length ? subdiv1.length - subdiv2.length : 0;
    }
}
