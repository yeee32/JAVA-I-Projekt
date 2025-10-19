package lab;


import javafx.geometry.Rectangle2D;

public interface HasCollision {
    Rectangle2D getHitbox();
    default boolean intersects(Rectangle2D rectangle) {
        return getHitbox().intersects(rectangle);
    }
}
