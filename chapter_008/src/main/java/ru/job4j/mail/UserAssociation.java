package ru.job4j.mail;

import java.util.*;

/**
 * UserAssociation.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserAssociation implements Merger {

    @Override
    public List<User> merge(List<User> users) {
        Set<User> del = new HashSet<>();
        Map<String, User> emails = new HashMap<>();
        users.forEach(u -> u.getMails().forEach(e -> {
                    User current = emails.put(e, u);
                    if (current != null) {
                        Set<User> repeated = new HashSet<>(current.getAllUsers());
                        repeated.add(current);
                        u.addUsers(repeated);
                        del.addAll(repeated);
                    }
                }
        ));
        users.removeAll(del);
        return users;
    }
}