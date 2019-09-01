package ru.job4j.vacancies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Class VacanciesStore.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class VacanciesStore implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(VacanciesStore.class.getName());

    private final Config config;
    private Connection connection;

    public VacanciesStore(Config config) throws ClassNotFoundException, SQLException {
        this.config = config;
        this.initConnection();
    }

    /**
     * Соединение с базой данных.
     * @return true - соединение успешно установлено, иначе - false.
     */
    private boolean initConnection() throws ClassNotFoundException, SQLException {
        this.config.init();
        Class.forName(this.config.get("driver-class-name"));
        this.connection = DriverManager.getConnection(
                this.config.get("url-vacancies"),
                this.config.get("username"),
                this.config.get("password")
        );
        this.connection.setAutoCommit(false);
        return this.connection != null;
    }

    /**
     * Добавление вакансий в базу данных.
     * @param vacancies список вакансий.
     */
    public void insertVacancies(List<Vacancy> vacancies) {
        try (PreparedStatement ps = this.connection.prepareStatement(
                "insert into vacancies_java (name, text, link, creation_time) values (?, ?, ?, ?) on conflict (name) do nothing")) {
            try {
                for (Vacancy vac : vacancies) {
                    ps.setString(1, vac.getName());
                    ps.setString(2, vac.getText());
                    ps.setString(3, vac.getLink());
                    ps.setLong(4, vac.getTime());
                    ps.addBatch();
                }
                ps.executeBatch();
                this.connection.commit();
            } catch (SQLException e) {
                this.connection.rollback();
            }
            LOG.info("Vacancies added to the database.");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод вазвращает время создания самой поздней вакансии из базы данных.
     * @return время создания самой поздней вакансии из базы данных.
     */
    public long getTimeOfLastVacancy() {
        long last = 0;
        LOG.info("Search for the date of the latest vacancy.");
        try (Statement st = this.connection.createStatement()) {
            ResultSet rs = st.executeQuery("select max(creation_time) as max_time from vacancies_java");
            if (rs.next()) {
                last = rs.getLong("max_time");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return last;
    }

    @Override
    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}