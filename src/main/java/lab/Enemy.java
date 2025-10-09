package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy {
    private GameScene gameScene;
    private Point2D position;
    private GraphicsContext gc;
    private Canvas canvas;

    double width = 20;
    double height = 20;

    public Enemy(GameScene gameScene, Point2D position){
        this.gameScene = gameScene;
        this.position = position;
    }

    public Color c = Color.RED;

    public void draw(GraphicsContext gc){
        gc.save();
        gc.setFill(c);
        gc.setStroke(c);
        gc.fillRect(position.getX() - width / 2,position.getY() - height / 2, width, height);
        gc.restore();
    }

    public void simulate(double delay){

    }

    public void setColor(){
        c = Color.GREEN;
    }

    public Rectangle2D getHitbox(){
        return new Rectangle2D(
            position.getX() - width / 2, position.getY() - height / 2, width, height
        );
    }
}
