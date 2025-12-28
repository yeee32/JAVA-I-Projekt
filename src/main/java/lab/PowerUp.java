package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.canvas.Canvas;

public class PowerUp implements HasCollision{
    private GameScene gameScene;
    private Point2D position;
    double width = 25;
    double height = 25;


    PowerUp(GameScene gameScene, Point2D position) {
        this.gameScene = gameScene;
        this.position = position;
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setFill(Color.ORANGE);
        gc.setStroke(Color.ORANGE);
        gc.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }


    public Rectangle2D getHitbox() {
        return new Rectangle2D(
            position.getX() - width / 2, position.getY() - height / 2, width, height
        );
    }
}
