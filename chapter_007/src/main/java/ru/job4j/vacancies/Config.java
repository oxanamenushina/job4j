package ru.job4j.vacancies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class Config.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Config {

    private static final Logger LOG = LogManager.getLogger(Config.class.getName());

    private final Properties values = new Properties();
    private final String prop;

    public Config(String prop) {
        this.prop = prop;
    }

    public void init() {
        try (InputStream in = ru.job4j.toxml.Config.class.getClassLoader().getResourceAsStream(this.prop)) {
            values.load(in);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}