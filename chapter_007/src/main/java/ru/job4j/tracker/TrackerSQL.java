package ru.job4j.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    /**
     * Соединение с базой данных.
     * @return true - соединение успешно установлено, иначе - false.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        this.createTables();
        return this.connection != null;
    }

    /**
     * Метод создает таблицы.
     */
    public void createTables() {
        try (Statement st = connection.createStatement()) {
            if (!this.tableExistenceCheck("tracker_items")) {
                st.execute("create table tracker_items("
                        + "id serial primary key, "
                        + "name varchar(100), "
                        + "description varchar(2000), "
                        + "creation_time int8);"
                );
            }
            if (!this.tableExistenceCheck("item_comments")) {
                st.execute("create table item_comments("
                        + "id serial primary key, "
                        + "comment_text varchar(2000), "
                        + "id_item int references tracker_items(id));"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверяет существует ли заданная таблица.
     * @param name название таблицы.
     * @return true - таблица уже существует,
     * false - таблицы с данным именем не существует.
     */
    private boolean tableExistenceCheck(String name) {
        boolean result = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, name, null)) {
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод, реализаущий добавление заявки в базу данных.
     * @param item заявка.
     * @return заявка.
     */
    @Override
    public Item add(Item item) {
        int count = 0;
        int ind = 0;
        Item result = new Item(item.getName(), item.getDesc(), item.getCreated());
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into tracker_items (name, description, creation_time) values (?, ?, ?)", new String[]{"id"})) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setLong(3, item.getCreated());
            count = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ind = rs.getInt(1);
                    result.setId("" + ind);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0 && item.getComments() != null && item.getComments().size() > 0) {
            this.insertComments(item, ind);
            result.setComments(item.getComments());
        }
        return result;
    }

    /**
     * Метод заменяет заявку с заданным идентификатором в базе данных.
     * @param id идентификатор заменяемой заявки.
     * @param item новая заявка.
     * @return true - заявка заменена, false - нет.
     */
    @Override
    public boolean replace(String id, Item item) {
        int count = 0;
        int required = Integer.parseInt(id);
        boolean rst = this.deleteComments(required);
        try (PreparedStatement ps = connection.prepareStatement(
                "update tracker_items set name = ?, description = ?, creation_time = ? where id = ?")) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setLong(3, item.getCreated());
            ps.setInt(4, required);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (count > 0 && rst) && ((item.getComments() == null) || this.insertComments(item, required));
    }

    /**
     * Метод находит заявку по идентификатору в базе данных и удаляет ее.
     * @param id идентификатор удаляемой заявки.
     * @return true - заявка удалена, false - нет.
     */
    @Override
    public boolean delete(String id) {
        int count = 0;
        int required = Integer.parseInt(id);
        boolean rst = this.deleteComments(required);
        try (PreparedStatement ps = connection.prepareStatement("delete from tracker_items where id = ?")) {
            ps.setInt(1, required);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0 && rst;
    }

    /**
     * Метод возвращает список всех заявок из базы данных.
     * @return список заявок.
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from tracker_items");
            items = this.getItems(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Метод осуществляет поиск заявок с заданным именем в базе данных.
     * @param key имя искомых заявок.
     * @return список заявок с заданным именем.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from tracker_items where name = ?")) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            items = this.getItems(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Метод осуществляет поиск заявки в базе данных по идентификатору.
     * @param id идентификатор искомой заявки.
     * @return найденный Item или null.
     */
    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from tracker_items where id = ?")) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String desc = rs.getString("description");
                long created = rs.getLong("creation_time");
                item = new Item(name, desc, created);
                item.setId(id);
                item.setComments(this.selectComments(Integer.parseInt(id)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод добавляет комментарии заявки в базу данных.
     * @param item заявка.
     * @param id идентификатор заявки.
     * @return true - комментарии успешно добавлены в базу данных, false - нет.
     */
    private boolean insertComments(Item item, int id) {
        boolean rst = false;
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into item_comments (comment_text, id_item) values (?, ?)")) {
            for (String comm : item.getComments()) {
                ps.setString(1, comm);
                ps.setInt(2, id);
                ps.addBatch();
            }
            int[] count = ps.executeBatch();
            rst = count.length > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rst;
    }

    /**
     * Метод удаляет комментарии заявки из базы данных.
     * @param id идентификатор заявки.
     * @return true - комментарии успешно удалены из базы данных, false - нет.
     */
    private boolean deleteComments(int id) {
        boolean rst = true;
        Item item = this.findById(Integer.toString(id));
        if (item.getComments() != null && item.getComments().size() > 0) {
            try (PreparedStatement ps = connection.prepareStatement("delete from item_comments where id_item = ?")) {
                ps.setInt(1, id);
                int count = ps.executeUpdate();
                rst = count > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rst;
    }

    /**
     * Метод находит в базе данных и возвращает
     * список комментариев заявки с заданным идентификатором.
     * @param id идентификатор заявки.
     * @return список комментариев.
     */
    private List<String> selectComments(int id) {
        List<String> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select comment_text from item_comments where id_item = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("comment_text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод возвращает список заявок.
     * @param rs записи из таблицы базы данных.
     * @return список заявок.
     */
    private List<Item> getItems(ResultSet rs) throws SQLException {
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            String desc = rs.getString("description");
            long created = rs.getLong("creation_time");
            Item item = new Item(name, desc, created);
            int id = rs.getInt("id");
            item.setId(Integer.toString(id));
            List<String> list = this.selectComments(id);
            item.setComments(list);
            items.add(item);
        }
        return items;
    }

    @Override
    public void close() {
        try {
           this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}