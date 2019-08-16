package ru.job4j.toxml;

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
public class ConvertXSQTTest {

    public void createXMLFile() {
        File file = new File("entries.xml");
        try (StoreSQL store = new StoreSQL(new Config())) {
            store.generate(2);
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

    public void createXSQTFile() {
        this.createXMLFile();
        File dest = new File("xsqtentries.xml");
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ConvertXSQT().convert(new File("entries.xml"), dest,
                new File(ConvertXSQT.class.getClassLoader().getResource("scheme.xsl").getPath()));
    }

    @Test
    public void whenConvertXSQTConvertThenCreatesFileXSQTWithEntries() {
        this.createXSQTFile();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("xsqtentries.xml"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder exp = new StringBuilder();
        exp.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<entries>")
                .append("<entry field=\"1\"/>")
                .append("<entry field=\"2\"/>")
                .append("</entries>")
                .append(System.lineSeparator());
        assertThat(sb.toString(), is(exp.toString()));
    }
}