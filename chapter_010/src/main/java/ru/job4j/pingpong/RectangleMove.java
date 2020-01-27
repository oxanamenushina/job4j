package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * RectangleMove.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RectangleMove implements Runnable {

    /**
     * Rectangle.
     */
    private final Rectangle rect;

    /**
     * The size of the field on the X axis.
     */
    private final int limX;

    /**
     * The size of the field on the Y axis.
     */
    private final int limY;

    /**
     * Rectangle directions along the X and Y axes.
     */
    private int[] direction = {1, 1};

    public RectangleMove(Rectangle rect, int limX, int limY) {
        this.rect = rect;
        this.limX = limX;
        this.limY = limY;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.setDirection();
            this.rect.setX(this.rect.getX() + this.direction[0]);
            this.rect.setY(this.rect.getY() + this.direction[1]);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * The method sets the direction to the rectangle.
     */
    private void setDirection() {
        this.direction[0] = this.rect.getX() == 0 ? 1 : this.rect.getX() + this.rect.getWidth() == this.limX ? -1
                : this.direction[0];
        this.direction[1] =  this.rect.getY() == 0 ? 1 : this.rect.getY() + this.rect.getHeight() == this.limY ? -1
                : this.direction[1];
    }
}