package ru.job4j.statistics;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Analyze {
    /**
     * Метод возвращает статистику об изменении коллекции.
     * @param previous начальные данные.
     * @param current измененные данные.
     * @return Info - количество добавленных, удаленных и измененных пользователей.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> map = previous.stream().collect(Collectors.toMap(n -> n.id, n -> n.name));
        current.forEach(n -> info.changed = isChanged(n.name, map.put(n.id, n.name)) ? ++info.changed : info.changed);
        info.added = map.size() - previous.size();
        info.deleted = map.size() - current.size();
        return info;
    }

    /**
     * Метод проверяет изменилось ли имя пользователя.
     * @param newVal новое имя.
     * @param oldVal старое имя или null, если пользователя с данным id небыло.
     * @return true - имя изменилось, false - нет.
     */
    private boolean isChanged(String newVal, String oldVal) {
       return oldVal != null && !newVal.equals(oldVal);
    }

    /**
     * Класс User.
     */
    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
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
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    /**
     * Класс Info.
     */
    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
