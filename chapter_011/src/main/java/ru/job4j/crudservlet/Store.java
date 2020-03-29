package ru.job4j.crudservlet;

import java.util.List;

/**
 * Store.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    /**
     * The method adds the user to the storage.
     * @param user the user to add.
     */
    void add(User user);

    /**
     * The method updates the user's data.
     * @param newUser the user to update.
     */
    void update(User newUser);

    /**
     * The method deletes the user.
     * @param user the user to delete.
     */
    void delete(User user);

    /**
     * The method returns a list of users.
     * @return a list of users.
     */
    List<User> findAll();

    /**
     * The method returns a user with the specified id.
     * @param id user ID.
     * @return user with the specified id.
     */
    User findById(int id);
}