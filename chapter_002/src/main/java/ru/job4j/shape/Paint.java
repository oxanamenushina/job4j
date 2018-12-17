package ru.job4j.shape;

/**
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * Метод прорисовывает сформированную строку фигуры.
     * @param shape
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    /**
     * Метод main.
     * @param args
     */
    public static void main(String[] args) {
        Paint figure = new Paint();
        figure.draw(new Square());
        figure.draw(new Triangle());
    }
}
