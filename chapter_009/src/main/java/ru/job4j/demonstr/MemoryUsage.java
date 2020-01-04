package ru.job4j.demonstr;

/**
 * MemoryUsage.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MemoryUsage {

    public static void info() {
        int kb = 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Heap utilization statistics [kB]:");
        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / kb);
        System.out.println("Free memory: " + runtime.freeMemory() / kb);
        System.out.println("Total memory: " + runtime.totalMemory() / kb);
        System.out.println("Max memory: " + runtime.maxMemory() / kb);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 4000; i++) {
            new User("User" + i, i);
        }
        info();
    }
}