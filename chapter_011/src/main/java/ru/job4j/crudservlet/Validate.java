package ru.job4j.crudservlet;

import java.util.List;

/**
 * Validate.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Validate {

    /**
     * The method checks the input for the add user operation.
     * @param user the user to add.
     * @return true - the user added to the storage,
     * false - the user didn't add to the storage.
     */
    boolean add(User user);

    /**
     * The method checks the input
     * for the update user operation.
     * @param newUser the user to update.
     * @return true - the user updated,
     * false - the user didn't update.
     */
    boolean update(User newUser);

    /**
     * The method checks the input
     * for the delete user operation.
     * @param user the user to delete.
     * @return true - the user deleted,
     * false - the user didn't delete.
     */
    boolean delete(User user);

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