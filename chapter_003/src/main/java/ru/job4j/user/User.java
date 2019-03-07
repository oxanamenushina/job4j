package ru.job4j.user;

/**
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User user) {
        int result = Integer.compare(this.age, user.age);
        return result != 0 ? result : this.name.compareTo(user.name);
    }
}
