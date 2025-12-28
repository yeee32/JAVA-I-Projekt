package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class SmallFighter extends EnemyEntity implements HasCollision{
    private double zigzagAmplitude = 100;
    private double zigzagSpeed = 2;
    private double time = 0;

    double width = 40;
    double height = 40;

    private static Image img;

//    static {
//        img = new Image(SmallFighter.class.getResourceAsStream("white_down.png"));
//    }

    public SmallFighter(GameScene gameScene, Point2D position) {
        super(gameScene, position, 25, 25);
        this.speedY = 300;
        this.c = Color.WHITE;
        img = getImg();
    }

    Image getImg(){
        if (img == null) {
            img = new Image(SmallFighter.class.getResourceAsStream("white_down.png"));
        }
        return img;
    }

    @Override
    public void simulate(double deltaTime) {
        time += deltaTime;
        double offsetX = Math.sin(time * zigzagSpeed * 2 * Math.PI) * zigzagAmplitude;
        position = position.add(offsetX * deltaTime, speedY * deltaTime);
    }

    public void draw(GraphicsContext gc){
        gc.save();
        gc.drawImage(img, position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }
}
