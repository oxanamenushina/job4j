package ru.job4j.crudservlet;

import java.util.List;

/**
 * ValidateService.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Validate {

    /**
     * The instance of ValidateService class.
     */
    private static final Validate VALIDATE = new ValidateService();

    /**
     * The instance of MemoryStore class.
     */
    private final Store store = DBStore.getInstance();

    /**
     * The template for the user name.
     */
    private final String nameTemplate = "\\w+";

    /**
     * The template for the user's login.
     */
    private final String loginTemplate = "[A-Za-z0-9]+[_A-Za-z0-9-]*";

    /**
     * The template for the user's email address.
     */
    private final String emailTemplate
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private ValidateService() {
    }

    /**
     * The method returns the instance of ValidateService.
     * @return the instance of ValidateService.
     */
    public static Validate getInstance() {
        return VALIDATE;
    }

    /**
     * The method checks the input for the add user operation.
     * @param user the user to add.
     * @return true - the user added to the storage,
     * false - the user didn't add to the storage.
     */
    @Override
    public boolean add(User user) {
        boolean result = this.isAbsent(user)
                && this.isRespond(user.getName(), this.nameTemplate)
                && this.isRespond(user.getLogin(), this.loginTemplate)
                && this.isRespond(user.getEmail(), this.emailTemplate);
        if (result) {
            this.store.add(user);
        }
        return result;
    }

    /**
     * The method checks the input
     * for the update user operation.
     * @param newUser the user to update.
     * @return true - the user updated,
     * false - the user didn't update.
     */
    @Override
    public boolean update(User newUser) {
        boolean result = this.findById(newUser.getId()) != null
                && (this.isRespond(newUser.getName(), this.nameTemplate)
                || this.isRespond(newUser.getLogin(), this.loginTemplate)
                || this.isRespond(newUser.getEmail(), this.emailTemplate)
                || (newUser.getPhotoId() != null && !newUser.getPhotoId().equals("")));
        if (result) {
            store.update(newUser);
        }
        return result;
    }

    /**
     * The method checks the input
     * for the delete user operation.
     * @param user the user to delete.
     * @return true - the user deleted,
     * false - the user didn't delete.
     */
    @Override
    public boolean delete(User user) {
        boolean result = this.findById(user.getId()) != null;
        if (result) {
            store.delete(user);
        }
        return result;
    }

    /**
     * The method returns a list of users.
     * @return a list of users.
     */
    @Override
    public List<User> findAll() {
        return this.store.findAll();
    }

    /**
     * The method returns a user with the specified id.
     * @param id user ID.
     * @return user with the specified id.
     */
    @Override
    public User findById(int id) {
        return this.store.findById(id);
    }

    /**
     * The method checks if the user
     * is absent in the storage.
     * @param user user.
     * @return true - the user is absent.
     */
    private boolean isAbsent(User user) {
        return this.findAll().stream().noneMatch(user::equals);
    }

    /**
     * The method checks if the entered value matches the pattern.
     * @param str the entered value.
     * @param template a template.
     * @return true - the entered value matches the pattern.
     */
    private boolean isRespond(String str, String template) {
        return str != null && str.matches(template);
    }
}