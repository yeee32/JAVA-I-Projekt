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
    double width = 30;
    double height = 30;
    private static Image img;
    private static int speed = 100;

    PowerUp(GameScene gameScene, Point2D position) {
        this.gameScene = gameScene;
        this.position = position;
        img = getImg();
    }

    Image getImg(){
        if (img == null) {
            img = new Image(SmallFighter.class.getResourceAsStream("pow.png"));
        }
        return img;
    }

    public void simulate(double deltaTime) {
        position = position.add(0, 100 * deltaTime);
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.drawImage(img, position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }


    public Rectangle2D getHitbox() {
        return new Rectangle2D(
            position.getX() - width / 2, position.getY() - height / 2, width, height
        );
    }
}
