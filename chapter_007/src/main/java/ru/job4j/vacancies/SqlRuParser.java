package ru.job4j.vacancies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class SqlRuParser.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SqlRuParser {

    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());

    /**
     * Запуск приложения.
     * @param args массив, содержащий название файла с настройками.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            SqlRuJob sqlRu = new SqlRuJob(args[0]);
            sqlRu.startScheduler();
        } else {
            LOG.error("Enter the name of the file with the settings.");
        }
    }
}