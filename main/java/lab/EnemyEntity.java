package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Random;


public abstract class EnemyEntity implements HasCollision{
    protected GameScene gameScene;
    protected Point2D position;

    protected double width;
    protected double height;

    protected double speedY; // vertical
    protected double speedX; // horizontal


    protected boolean alive = true;
    protected Color c;

    protected EnemyEntity(GameScene gameScene, Point2D position, int w, int h) {
        this.gameScene = gameScene;
        this.position = position;
        this.width = w;
        this.height = h;
        this.speedY = 100; // default vertical speed
        this.speedX = 0;   // default horizontal speed
    }


    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setFill(c);
        gc.setStroke(c);
        gc.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }

    public abstract void simulate(double deltaTime);

    public Rectangle2D getHitbox() {
        return new Rectangle2D(position.getX() - width/2, position.getY() - height/2, width, height);
    }

    public Point2D getPosition() {
        return position;
    }
}
