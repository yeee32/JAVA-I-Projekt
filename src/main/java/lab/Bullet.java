package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public abstract class Bullet implements HasCollision {
    protected Point2D position;
    protected Point2D velocity;
    protected double width = 10;
    protected double height = 15;
    protected static Image image;

    public Bullet(Point2D position, Point2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void simulate(double deltaTime) {
        position = position.add(velocity.multiply(deltaTime));
    }

    public Rectangle2D getHitbox() {
        return new Rectangle2D(
            position.getX() - width / 2,
            position.getY() - height / 2,
            width,
            height
        );
    }

    public abstract void draw(GraphicsContext gc);

    public Point2D getPosition() {
        return position;
    }
}
