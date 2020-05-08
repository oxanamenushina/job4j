package ru.job4j.crudservlet;

import java.util.Objects;

/**
 * User.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class User {

    /**
     * User ID.
     */
    private int id;

    /**
     * User name.
     */
    private String name;

    /**
     * User's login.
     */
    private String login;

    /**
     * User's email address.
     */
    private String email;

    /**
     * User's photo.
     */
    private String photoId;

    /**
     * Creation date.
     */
    private String createDate;

    public User() {
    }

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public User(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public User(int id, String name, String login, String email, String photoId, String createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.photoId = photoId;
        this.createDate = createDate;
    }

    public User(int id, String name, String login, String email, String photoId) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.photoId = photoId;
    }

    public User(String name, String login, String email, String photoId) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.photoId = photoId;
    }

    /**
     * The method returns user ID.
     * @return user ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * The method returns user name.
     * @return user name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The method returns user's login.
     * @return user login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * The method returns user's email address.
     * @return user's email address.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * The method returns a filename.
     * @return a filename.
     */
    public String getPhotoId() {
        return this.photoId;
    }

    /**
     * The method returns creation date.
     * @return creation date.
     */
    public String getCreateDate() {
        return this.createDate;
    }

    /**
     * The method sets user ID.
     * @param id user ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The method sets user name.
     * @param name user name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method sets user's login.
     * @param login user login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * The method sets user's email address.
     * @param email user's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The method sets a filename.
     * @param photoId a filename.
     */
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    /**
     * The method sets creation date.
     * @param createDate creation date.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}