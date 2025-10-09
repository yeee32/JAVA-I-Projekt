package lab;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.canvas.Canvas;

public class Player {
    private GameScene gameScene;
    private Point2D position;
    private GraphicsContext gc;
    private Canvas canvas;
    private Bullet bullet;

    double width = 20;
    double height = 20;

    public Player(GameScene gameScene, Point2D position){
        this.gameScene = gameScene;
        this.position = position;
    }

    public void draw(GraphicsContext gc){
        gc.save();
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.fillRect(position.getX() - width / 2,position.getY() - height / 2, width, height);
        gc.restore();
    }

    double movementSpeed = 150;

    public void moveUp(double deltaTime){
        position = position.add(0, -movementSpeed * deltaTime);
    }

    public void moveDown(double deltaTime){
        position = position.add(0, movementSpeed * deltaTime);
    }

    public void moveLeft(double deltaTime){
        position = position.add(-movementSpeed * deltaTime, 0);
    }

    public void moveRight(double deltaTime){
        position = position.add(movementSpeed * deltaTime, 0);
    }

    public void shoot(){
        Bullet bullet = new Bullet(gameScene, position); // create a bullet at player pos
        gameScene.addBullet(bullet);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Rectangle2D getHitbox(){
        return new Rectangle2D(
            position.getX() - width / 2, position.getY() - height / 2, width, height
        );
    }
}
