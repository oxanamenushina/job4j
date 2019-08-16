package ru.job4j.toxml;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StoreXMLTest {

    @Before
    public void createXMLFile() {
        try (StoreSQL store = new StoreSQL(new Config())) {
            store.generate(2);
            File file = new File("entries.xml");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            StoreXML xml = new StoreXML(file);
            xml.save(store.load());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenStoreXMLSaveThenCreatesFileXMLWithEntries() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("entries.xml"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder exp = new StringBuilder();
        exp.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<entries>").append(System.lineSeparator())
                .append("    <entry>").append(System.lineSeparator())
                .append("        <field>1</field>").append(System.lineSeparator())
                .append("    </entry>").append(System.lineSeparator())
                .append("    <entry>").append(System.lineSeparator())
                .append("        <field>2</field>").append(System.lineSeparator())
                .append("    </entry>").append(System.lineSeparator())
                .append("</entries>").append(System.lineSeparator());
        assertThat(sb.toString(), is(exp.toString()));
    }
}