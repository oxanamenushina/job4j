package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByPhone() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Vasiliy", "Ivanov", "123789", "Moscow")
        );
        var persons = phones.find("378");
        assertThat(persons.iterator().next().getSurname(), is("Ivanov"));
    }

    @Test
    public void whenFindByAddress() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Vasiliy", "Ivanov", "123789", "Moscow")
        );
        var persons = phones.find("Mos");
        assertThat(persons.iterator().next().getSurname(), is("Ivanov"));
    }
}
