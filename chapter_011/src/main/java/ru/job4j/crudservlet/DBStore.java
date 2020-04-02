package ru.job4j.crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

/**
 * DBStore.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DBStore implements Store {

    /**
     * The instance of BasicDataSource class.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * The instance of DBStore class.
     */
    private static final Store INSTANCE = new DBStore();

    private DBStore() {
        Properties prop = new Properties();
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SOURCE.setDriverClassName(prop.getProperty("driver-class-name"));
        SOURCE.setUrl(prop.getProperty("url"));
        SOURCE.setUsername(prop.getProperty("username"));
        SOURCE.setPassword(prop.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.createTable();
    }

    /**
     * The method returns the instance of DBStore.
     * @return the instance of DBStore.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

    /**
     * The method adds the user to the storage.
     * @param user the user to add.
     */
    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "insert into user_servlet (name, login, email, creation_date) values (?, ?, ?, ?)")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method updates the user's data.
     * @param newUser the user to update.
     */
    @Override
    public void update(User newUser) {
        User oldUser = this.findById(newUser.getId());
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "update user_servlet set name = ?, login = ?, email = ? where id = ?")) {
            st.setString(1, newUser.getName() == null ? oldUser.getName() : newUser.getName());
            st.setString(2, newUser.getLogin() == null ? oldUser.getLogin() : newUser.getLogin());
            st.setString(3, newUser.getEmail() == null ? oldUser.getEmail() : newUser.getEmail());
            st.setInt(4, newUser.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method deletes the user.
     * @param user the user to delete.
     */
    @Override
    public void delete(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("delete from user_servlet where id = ?")) {
            st.setInt(1, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method returns a list of users.
     * @return a list of users.
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from user_servlet");
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("login"),
                        rs.getString("email"), rs.getString("creation_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * The method returns a user with the specified id.
     * @param id user ID.
     * @return user with the specified id.
     */
    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("select * from user_servlet where id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(id, rs.getString("name"), rs.getString("login"),
                        rs.getString("email"), rs.getString("creation_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * The method creates the table.
     */
    private void createTable() {
        try (Connection con = SOURCE.getConnection();
             Statement st = con.createStatement()) {
            st.execute("create table if not exists user_servlet ("
                    + "id serial primary key not null,"
                    + "name varchar(250),"
                    + "login varchar(250),"
                    + "email varchar(250),"
                    + "creation_date varchar(50)"
                    + ");"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}