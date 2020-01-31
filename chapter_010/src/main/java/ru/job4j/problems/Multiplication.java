package ru.job4j.problems;

public class Multiplication {

    private int result = 1;

    public void calculate(int number) {
        this.result *= number;
    }

    public int getResult() {
        return result;
    }
}