package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * UserStorage.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {

    /**
     * User storage.
     */
    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    /**
     * The method adds the user to the storage.
     * @param user the user to add.
     * @return true - the user added to the storage,
     * false - the user didn't add to the storage.
     */
    public synchronized boolean add(User user) {
        return user != null && this.users.putIfAbsent(user.getId(), user) == null;
    }

    /**
     * The method updates the user.
     * @param user the user to update.
     * @return true - the user updated,
     * false - the user didn't update.
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        if (user != null && this.users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * The method deletes the user.
     * @param user the user to delete.
     * @return true - the user deleted,
     * false - the user didn't delete.
     */
    public synchronized boolean delete(User user) {
        return user != null && this.users.remove(user.getId(), user);
    }

    /**
     * The method transfers the amount from the account
     * of one user to the account of another user.
     * @param fromId ID of the user whose account
     * the amount is being withdrawn from.
     * @param toId ID of the user to whose account
     * the amount will be added.
     * @param amount the amount.
     * @return true - the amount transferred,
     * false - the amount didn't transfer.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (this.users.containsKey(fromId) && this.users.containsKey(toId) && this.users.get(fromId) != null
                && this.users.get(toId) != null && this.users.get(fromId).getAmount() >= amount) {
            this.users.get(fromId).setAmount(this.users.get(fromId).getAmount() - amount);
            this.users.get(toId).setAmount(this.users.get(toId).getAmount() + amount);
            result = true;
        }
        return result;
    }

    /**
     * The method returns a user with the specified id.
     * @param id user ID.
     * @return user with the specified id.
     */
    public synchronized User get(int id) {
        return this.users.get(id);
    }
}