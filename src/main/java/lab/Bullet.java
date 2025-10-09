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


public class Bullet {
    private GameScene gameScene;
    private Point2D position;
    private GraphicsContext gc;
    private Canvas canvas;

    double width = 10;
    double height = 15;
    double speed = 300;


    public Bullet(GameScene gameScene, Point2D playerPosition){
        this.position = new Point2D(playerPosition.getX(), playerPosition.getY() - 20);
    }

    public void moveUp(double delay) {
        position = position.add(0, -speed * delay);
    }

    public void draw(GraphicsContext gc){
        gc.save();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.GREEN);
        gc.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }

    public void simulate(Double delay){
        moveUp(delay);
    }

    public Point2D getPosition(){
        return position;
    }

    public Rectangle2D getHitbox(){
        return new Rectangle2D(
            position.getX() - width / 2, position.getY() - height / 2, width, height
        );
    }

}
