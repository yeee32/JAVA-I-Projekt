package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public abstract class EnemyEntity implements HasCollision{
    private static final Random RANDOM = new Random();

    protected final GameScene gameScene;
    protected Point2D position;
    private GraphicsContext gc;
    private Canvas canvas;

    double width = 20;
    double height = 20;
    double speed = RANDOM.nextDouble(20, 70);

    protected EnemyEntity(GameScene gameScene, Point2D position) {
        this.gameScene = gameScene;
        this.position = position;
    }

    public Color c = Color.RED;

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setFill(c);
        gc.setStroke(c);
        gc.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }

    public void simulate(double delay) {
        position = position.add(RANDOM.nextDouble(-10,10) * delay, speed * delay);
        if ((position.getX() > gameScene.getSize().getWidth() - width / 2) || (position.getX() < 0 + width / 2)) {
            speed = -speed;
        }
    }

    public void setColor(Color col) {
        c = col;
    }

    public Rectangle2D getHitbox() {
        return new Rectangle2D(
            position.getX() - width / 2, position.getY() - height / 2, width, height
        );
    }
}
