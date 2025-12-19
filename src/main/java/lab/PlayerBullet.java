package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerBullet extends Bullet {
    private static Image image;

    public PlayerBullet(Point2D position, Point2D velocity) {
        super(position, velocity);
        if (image == null) {
            image = new Image(PlayerBullet.class.getResourceAsStream("bullet.png"));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, position.getX() - width/2, position.getY() - height/2, width, height);
    }
}
