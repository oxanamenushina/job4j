package ru.job4j.shape;
/**
 * Треугольник.
 */
public class Triangle implements Shape {
    /**
     * Метод создает строку символов в форме треугольника.
     * @return строку символов в форме треугольника.
     */
    public String draw() {
        StringBuilder builder = new StringBuilder();
        builder.append("   *   ").append(System.lineSeparator());
        builder.append("  ***  ").append(System.lineSeparator());
        builder.append(" ***** ").append(System.lineSeparator());
        builder.append("*******");
        return builder.toString();
    }
}
