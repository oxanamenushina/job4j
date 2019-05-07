package ru.job4j.generic;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RoleStore<Role> extends AbstractStore {

    public RoleStore(SimpleArray<Role> store) {
        super(store);
    }
}
