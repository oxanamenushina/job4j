package ru.job4j.menu;

public class ElementAction implements Action {

    private String info;

    public ElementAction(String info) {
        this.info = info;
    }

    @Override
    public void execute() {
        System.out.print(this.info + System.lineSeparator());
    }
}
