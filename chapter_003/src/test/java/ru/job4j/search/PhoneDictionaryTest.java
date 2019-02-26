package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Vasiliy", "Ivanov", "123789", "Moscow")
        );
        List<Person> persons = phones.find("378");
        assertThat(persons.iterator().next().getSurname(), is("Ivanov"));
    }

    @Test
    public void whenFindByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Vasiliy", "Ivanov", "123789", "Moscow")
        );
        List<Person> persons = phones.find("Mos");
        assertThat(persons.iterator().next().getSurname(), is("Ivanov"));
    }
}
