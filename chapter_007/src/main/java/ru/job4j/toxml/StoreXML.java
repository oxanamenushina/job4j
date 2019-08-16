package ru.job4j.toxml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StoreXML {

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Метод сохраняет данные из list в файл XML target.
     * @param list список всех Entry.
     */
    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), this.target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}