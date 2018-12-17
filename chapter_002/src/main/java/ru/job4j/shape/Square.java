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
        builder.append("*******\r\n");
        builder.append("*******\r\n");
        builder.append("*******\r\n");
        builder.append("*******\r\n");
        builder.append("*******\r\n");
        builder.append("*******\r\n");
        builder.append("*******");
        return  builder.toString();
    }
}
