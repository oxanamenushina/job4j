package ru.job4j.students;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StudentGrouping {
    /**
     * Метод возвращает лист со студентами, у которых бал аттестата больше bound.
     * @param students список студентов.
     * @param bound проходной бал.
     * @return ллист со студентами, у которых бал аттестата больше bound.
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().sorted().flatMap(Stream::ofNullable)
                .takeWhile(student -> student.getScope() > bound).collect(Collectors.toList());
    }
}
