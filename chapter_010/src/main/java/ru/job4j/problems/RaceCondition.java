package ru.job4j.problems;

public class RaceCondition implements Runnable {

    private int number;
    private Multiplication mul;

    public RaceCondition(int number, Multiplication mul) {
        this.number = number;
        this.mul = mul;
    }

    @Override
    public void run() {
        for (int i = this.number; i > 0; i--) {
            this.mul.calculate(i);
        }
    }

    public static void main(String[] args) {
        Multiplication f = new Multiplication();
        RaceCondition rc = new RaceCondition(5, f);
        for (int i = 0; i < 3; i++) {
            new Thread(rc).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Expected value:   120");
        System.out.println("Result:   " + f.getResult());
    }

}
