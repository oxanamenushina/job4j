package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ConfigTest {

    @Test
    public void whenLoadThenMapHasFivePairs() {
        String[] str = {"hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect",
                "hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio",
                "hibernate.connection.driver_class=org.postgresql.Driver",
                "hibernate.connection.username=postgres",
                "hibernate.connection.password=password"};
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("server.log"))) {
            for (String s : str) {
                bw.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config config = new Config("app.properties.txt");
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }
}
