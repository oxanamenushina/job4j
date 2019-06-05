package ru.job4j.statistics;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class AnalyzeTest {

    @Test
    public void whenOneAddedOneChangedThenInfo110() {
        Analyze analyze = new Analyze();
        Analyze.User user1 = new Analyze.User(1, "Anton");
        Analyze.User user2 = new Analyze.User(2, "Ivan");
        Analyze.User user3 = new Analyze.User(3, "Kate");
        Analyze.User user4 = new Analyze.User(1, "Olga");
        Analyze.Info result = analyze.diff(List.of(user1, user3), List.of(user2, user3, user4));
        assertThat(result.getAdded(), is(1));
        assertThat(result.getChanged(), is(1));
        assertThat(result.getDeleted(), is(0));
    }

    @Test
    public void whenOneAddedOneDeletedThenInfo101() {
        Analyze analyze = new Analyze();
        Analyze.User user1 = new Analyze.User(1, "Anton");
        Analyze.User user2 = new Analyze.User(2, "Ivan");
        Analyze.User user3 = new Analyze.User(3, "Kate");
        Analyze.Info result = analyze.diff(List.of(user1, user2), List.of(user2, user3));
        assertThat(result.getAdded(), is(1));
        assertThat(result.getChanged(), is(0));
        assertThat(result.getDeleted(), is(1));
    }

    @Test
    public void whenOneChangedOneDeletedThenInfo011() {
        Analyze analyze = new Analyze();
        Analyze.User user1 = new Analyze.User(1, "Anton");
        Analyze.User user2 = new Analyze.User(2, "Ivan");
        Analyze.User user3 = new Analyze.User(3, "Kate");
        Analyze.User user4 = new Analyze.User(3, "Denis");
        Analyze.Info result = analyze.diff(List.of(user1, user2, user3), List.of(user2, user4));
        assertThat(result.getAdded(), is(0));
        assertThat(result.getChanged(), is(1));
        assertThat(result.getDeleted(), is(1));
    }

    @Test
    public void whenTwoAddedTwoChangedTwoDeletedThenInfo222() {
        Analyze analyze = new Analyze();
        Analyze.User user1 = new Analyze.User(1, "Anton");
        Analyze.User user2 = new Analyze.User(2, "Ivan");
        Analyze.User user3 = new Analyze.User(3, "Kate");
        Analyze.User user4 = new Analyze.User(4, "Denis");
        Analyze.User user5 = new Analyze.User(5, "Olga");
        Analyze.User user6 = new Analyze.User(6, "Xenia");
        Analyze.User user7 = new Analyze.User(7, "Roman");
        Analyze.User user8 = new Analyze.User(1, "Nikolay");
        Analyze.User user9 = new Analyze.User(7, "Max");
        Analyze.Info result
                = analyze.diff(List.of(user1, user2, user3, user5, user7), List.of(user3, user4, user6, user8, user9));
        assertThat(result.getAdded(), is(2));
        assertThat(result.getChanged(), is(2));
        assertThat(result.getDeleted(), is(2));
    }

}
