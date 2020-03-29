package ru.job4j.crudservlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MemoryStore.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store {

    /**
     * The instance of MemoryStore class.
     */
    private static final Store STORE = new MemoryStore();

    /**
     * User storage.
     */
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * The counter.
     */
    private final AtomicInteger number = new AtomicInteger();

    private MemoryStore() {
    }

    /**
     * The method returns the instance of MemoryStore.
     * @return the instance of MemoryStore.
     */
    public static Store getInstance() {
        return STORE;
    }

    /**
     * The method adds the user to the storage.
     * @param user the user to add.
     */
    @Override
    public void add(User user) {
        user.setId(this.number.getAndIncrement());
        user.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
        this.users.put(user.getId(), user);
    }

    /**
     * The method updates the user's data.
     * @param newUser the user to update.
     */
    @Override
    public void update(User newUser) {
        User oldUser = this.findById(newUser.getId());
        oldUser.setName(newUser.getName() == null ? oldUser.getName() : newUser.getName());
        oldUser.setLogin(newUser.getLogin() == null ? oldUser.getLogin() : newUser.getLogin());
        oldUser.setEmail(newUser.getEmail() == null ? oldUser.getEmail() : newUser.getEmail());
    }

    /**
     * The method deletes the user.
     * @param user the user to delete.
     */
    @Override
    public void delete(User user) {
        this.users.remove(user.getId());
    }

    /**
     * The method returns a list of users.
     * @return a list of users.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.users.values());
    }

    /**
     * The method returns a user with the specified id.
     * @param id user ID.
     * @return user with the specified id.
     */
    @Override
    public User findById(int id) {
        return this.users.get(id);
    }
}