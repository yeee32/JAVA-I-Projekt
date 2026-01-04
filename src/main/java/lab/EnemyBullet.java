package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Point2D;

public class EnemyBullet extends Bullet {
    private Image image;

    public EnemyBullet(Point2D position, Point2D velocity) {
        super(position, velocity);
        if (image == null) {
            image = new Image(EnemyBullet.class.getResourceAsStream("enemy_bullet.png"));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, position.getX() - width/2, position.getY() - height/2, width, height);
    }
}
