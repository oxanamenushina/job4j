package ru.job4j.problems;

public class Obj {

    private final int number;

    public Obj(int number) {
        this.number = number;
    }

    public synchronized void method1(Obj obj) {
        System.out.println("Метод method1 " + obj.getNumber());
        obj.method2(this);
    }

    public synchronized void method2(Obj obj) {
        System.out.println("Метод method2 " + obj.getNumber());

    }

    public int getNumber() {
        return number;
    }
}