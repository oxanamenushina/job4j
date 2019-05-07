package ru.job4j.generic;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserStore<User> extends AbstractStore {

    public UserStore(SimpleArray<User> store) {
        super(store);
    }
}
