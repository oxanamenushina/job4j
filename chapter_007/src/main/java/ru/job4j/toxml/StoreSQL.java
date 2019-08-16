package ru.job4j.toxml;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connect;

    /**
     * Конструктор.
     * @param config настройки для подключения к базе данных.
     */
    public StoreSQL(Config config) throws SQLException {
        this.config = config;
        this.initConnection();
        this.createTable();
    }

    /**
     * Метод вставляет в таблицу entry size записей со значениями 1..size.
     * Если в таблице уже есть записи, то они удаляются перед вставкой.
     * @param size количество записей.
     */
    public void generate(int size) {
        try (PreparedStatement ps = this.connect.prepareStatement("insert into entry (field) values (?)")) {
            try {
                for (int i = 0; i < size; i++) {
                    ps.setInt(1, i + 1);
                    ps.addBatch();
                }
                ps.executeBatch();
                this.connect.commit();
            } catch (SQLException e1) {
                this.connect.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает список всех Entry.
     * @return лист Entry.
     */
    public List<Entry> load() {
        List<Entry> entries = new ArrayList<>();
        try (Statement statement = this.connect.createStatement()) {
            try {
                ResultSet rs = statement.executeQuery("select * from entry");
                while (rs.next()) {
                    entries.add(new Entry(rs.getInt("field")));
                }
                this.connect.commit();
            } catch (SQLException e1) {
                this.connect.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Соединение с базой данных.
     */
    private void initConnection() throws SQLException {
        this.config.init();
        this.connect = DriverManager.getConnection(this.config.get("url"));
        this.connect.setAutoCommit(false);
    }

    /**
     * Метод создает таблицу в базе данных.
     */
    private void createTable() {
        try (Statement st = this.connect.createStatement()) {
            try {
                st.execute("create table if not exists entry(field integer)");
                st.execute("delete from entry");
                this.connect.commit();
            } catch (SQLException e1) {
                this.connect.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (this.connect != null) {
                this.connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}