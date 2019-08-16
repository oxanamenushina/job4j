package ru.job4j.toxml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SAXSum {
    private File file;

    public SAXSum(File file) {
        this.file = file;
    }

    /**
     * Метод парсит входной файл и выводит арифметическую
     * сумму всех значений field в консоль.
     * @return сумма значений.
     */
    public int saxPars() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(this.file, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        int sum = handler.getSum();
        System.out.println("Арифметическая сумма всех значений field: " + sum);
        return sum;
    }

    /**
     * Класс Handler.
     */
    private class Handler extends DefaultHandler {
        private int sum = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("entry")) {
                this.sum += Integer.parseInt(attributes.getValue("field"));
            }
        }

        public int getSum() {
            return this.sum;
        }
    }
}