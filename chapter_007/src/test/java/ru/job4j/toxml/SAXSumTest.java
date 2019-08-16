package ru.job4j.toxml;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SAXSumTest {

    public void createXMLFile() {
        File file = new File("entries.xml");
        try (StoreSQL store = new StoreSQL(new Config())) {
            store.generate(3);
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
    public void whenGeneratedThreeEntriesThenSumSix() {
        this.createXSQTFile();
        SAXSum sum = new SAXSum(new File("xsqtentries.xml"));
        assertThat(sum.saxPars(), is(6));
    }
}