package ru.job4j.crudservlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ValidateStab.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateStub implements Validate {

    /**
     * A storage.
     */
    private final Map<Integer, User> store = new HashMap<>();

    /**
     * A counter.
     */
    private int ids = 0;

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

    @Override
    public boolean add(User user) {
        boolean result = this.isRespond(user.getName(), this.nameTemplate)
                && this.isRespond(user.getLogin(), this.loginTemplate)
                && this.isRespond(user.getEmail(), this.emailTemplate);
        if (result) {
            user.setId(this.ids++);
            this.store.put(user.getId(), user);
        }
        return result;
    }

    @Override
    public boolean update(User newUser) {
        User oldUser = this.findById(newUser.getId());
        boolean result = oldUser != null
                && (this.isRespond(newUser.getName(), this.nameTemplate)
                || this.isRespond(newUser.getLogin(), this.loginTemplate)
                || this.isRespond(newUser.getEmail(), this.emailTemplate));
        if (result) {
            oldUser.setName(newUser.getName() != null ? newUser.getName() : oldUser.getName());
            oldUser.setLogin(newUser.getLogin() != null ? newUser.getLogin() : oldUser.getLogin());
            oldUser.setEmail(newUser.getEmail() != null ? newUser.getEmail() : oldUser.getEmail());
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        User deleted = this.store.remove(user.getId());
        return deleted != null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store.values());
    }

    @Override
    public User findById(int id) {
        return this.store.get(id);
    }

    @Override
    public boolean isPermit(String login, String password) {
        return this.findAll().stream().anyMatch(u -> login.equals(u.getLogin()) && password.equals(u.getPassword()));
    }

    private boolean isRespond(String str, String template) {
        return str != null && str.matches(template);
    }
}