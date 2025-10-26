package lab;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;


public class SmallFighter extends EnemyEntity implements HasCollision{
    private static final Random RANDOM = new Random();

    public SmallFighter(GameScene gameScene, Point2D position) {
        super(gameScene, position);
        this.width = 25;
        this.height = 25;
        this.speed = RANDOM.nextDouble(300,375);
        setColor(Color.VIOLET);
    }

    private double randX = RANDOM.nextDouble(-100,100);
    // random limit when the small fighter turns around
    private double lim = gameScene.getSize().getWidth() * RANDOM.nextDouble(0.6, 0.9);

    public void simulate(double delay){
        position = position.add(randX * delay, speed * delay);
        if (position.getY() > lim) {
            speed = -speed;
        }
    }
}
