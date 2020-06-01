package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * CinemaStorage.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CinemaStorage implements Storage {

    private static final Logger LOG = LogManager.getLogger(CinemaStorage.class.getName());

    /**
     * The instance of BasicDataSource class.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * The instance of CinemaStorage class.
     */
    private static final Storage INSTANCE = new CinemaStorage();

    private CinemaStorage() {
        Properties prop = new Properties();
        try (InputStream in = CinemaStorage.class.getClassLoader().getResourceAsStream("app.properties")) {
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
        this.createPlaces(3, 3);
    }

    /**
     * The method returns an instance of CinemaStorage.
     * @return an instance of CinemaStorage.
     */
    public static Storage getInstance() {
        return INSTANCE;
    }

    /**
     * The method adds the account to the storage.
     * @param account an account to add.
     */
    @Override
    public void addAccount(Account account) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "insert into cinema_accounts (name, phone_number, id_place) values (?, ?, ?) "
                             + "on conflict (id_place) do nothing")) {
            connection.setAutoCommit(false);
            try {
                st.setString(1, account.getName());
                st.setString(2, account.getPhoneNumber());
                st.setInt(3, this.getId(account.getPlace()));
                st.execute();
                connection.commit();
            } catch (SQLException e1) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method returns a list of places.
     * @return a list of places.
     */
    @Override
    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select p.row, p.place_number, p.price, a.id from cinema_places as p "
                    + "left outer join cinema_accounts as a on p.id = a.id_place order by p.id");
            while (rs.next()) {
                int row = rs.getInt(1);
                int number = rs.getInt(2);
                int price = rs.getInt(3);
                rs.getInt(4);
                places.add(new Place(row, number, price, !rs.wasNull()));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return places;
    }

    /**
     * The method returns the id of the given place.
     * @param place a place.
     * @return the id of the given place.
     */
    private int getId(Place place) {
        int id = -1;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "select id from cinema_places where row = ? and place_number = ?")) {
            st.setInt(1, place.getRow());
            st.setInt(2, place.getPlaceNumber());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return id;
    }

    /**
     * The method adds places to the storage.
     * @param row number of rows.
     * @param number number of seats in a row.
     */
    private void createPlaces(int row, int number) {
        this.clearTables();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "insert into cinema_places (row, place_number, price) values (?, ?, ?)")) {
            int price;
            for (int i = 0; i < row; i++) {
                price = i >= 2 && i < 8 ? 400 : i >= 8 ? 300 : 500;
                for (int j = 0; j < number; j++) {
                    st.setInt(1, i + 1);
                    st.setInt(2, j + 1);
                    st.setInt(3, price);
                    st.addBatch();
                }
            }
            st.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method clears the tables.
     */
    private void clearTables() {
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            st.execute("delete from cinema_accounts");
            st.execute("delete from cinema_places");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}