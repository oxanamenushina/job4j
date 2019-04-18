package ru.job4j.students;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StudentGroupingTest {

    @Test
    public void whenBound4Then1Student() {
        Student st1 = new Student("Ivan", 3);
        Student st2 = new Student("Anton", 5);
        Student st3 = new Student("Olga", 4);
        StudentGrouping grouping = new StudentGrouping();
        assertThat(grouping.levelOf(List.of(st1, st2, st3), 4), is(List.of(st2)));
    }

    @Test
    public void whenBound3Then3Students() {
        Student st1 = new Student("Ivan", 3);
        Student st2 = new Student("Anton", 5);
        Student st3 = new Student("Olga", 4);
        Student st4 = new Student("Oleg", 3);
        Student st5 = new Student("Irina", 5);
        StudentGrouping grouping = new StudentGrouping();
        assertThat(grouping.levelOf(List.of(st1, st2, st3, st4, st5), 3), is(List.of(st2, st5, st3)));
    }
}
