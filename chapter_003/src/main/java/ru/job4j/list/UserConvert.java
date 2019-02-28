package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {
    /**
     * Метод принимает в себя список пользователей
     * и конвертирует его в Map с ключом Integer id и соответствующим ему User.
     * @param list двумерный массив
     * @return лист с элементами двумерного массива
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> userMap = new HashMap<>();
        for (User user : list) {
            userMap.put(user.getId(), user);
        }
        return userMap;
    }
}
