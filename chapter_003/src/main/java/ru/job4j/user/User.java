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
    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.name == null ? 0 : name.hashCode());
        result = 31 * result + this.age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj instanceof User) {
            User second = (User) obj;
            result = (this.name != null && second.name != null && this.name.equals(second.name) && this.age == second.age)
                    || (this.name == null && second.name == null && this.age == second.age);
        }
        return result;
    }

    @Override
    public int compareTo(User user) {
        int result = this.age > user.age ? 1 : this.age < user.age ? -1 : 0;
        return result != 0 ? result : this.name.compareTo(user.name);
    }
}
