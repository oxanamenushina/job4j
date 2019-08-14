package ru.job4j.shape;
/**
 * Квадрат.
 */
public class Square implements Shape {
    /**
     * Метод создает строку символов в форме квадрата.
     * @return строку символов в форме квадрата.
     */
    public String draw() {
        StringBuilder builder = new StringBuilder();
        builder.append("*******").append(System.lineSeparator());
        builder.append("*******").append(System.lineSeparator());
        builder.append("*******").append(System.lineSeparator());
        builder.append("*******").append(System.lineSeparator());
        builder.append("*******").append(System.lineSeparator());
        builder.append("*******").append(System.lineSeparator());
        builder.append("*******");
        return  builder.toString();
    }
}
