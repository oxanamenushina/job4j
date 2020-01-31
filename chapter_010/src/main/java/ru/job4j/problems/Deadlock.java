package ru.job4j.problems;

public class Deadlock {

    public static void main(String[] args) {
        Obj first = new Obj(1);
        Obj second = new Obj(2);

        new Thread() {
            @Override
            public void run() {
                first.method1(second);
                System.out.println("Первый поток должен быть заблокирован");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                second.method1(first);
                System.out.println("Второй поток должен быть заблокирован");
            }
        }.start();
    }
}