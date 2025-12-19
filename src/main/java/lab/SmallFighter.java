package lab;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;


public class SmallFighter extends EnemyEntity implements HasCollision{
    private double zigzagAmplitude = 80;
    private double zigzagSpeed = 2; // cycles/sec
    private double time = 0;

    public SmallFighter(GameScene gameScene, Point2D position) {
        super(gameScene, position, 25, 25);
        this.speedY = 300;
        this.c = Color.VIOLET;
    }

    @Override
    public void simulate(double deltaTime) {
        time += deltaTime;
        double offsetX = Math.sin(time * zigzagSpeed * 2 * Math.PI) * zigzagAmplitude * deltaTime;
        position = position.add(offsetX, speedY * deltaTime);

        if (position.getY() > gameScene.getSize().getHeight() + height) {
            alive = false;
        }
    }
}
