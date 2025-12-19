package lab;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class MediumBomber extends EnemyEntity implements HasCollision {
    private double shootTimer = 0;
    private double shootCooldown = 2; // seconds

    public MediumBomber(GameScene gameScene, Point2D position) {
        super(gameScene, position, 35, 35);
        this.speedY = 150;
        this.c = Color.PURPLE;
    }

    @Override
    public void simulate(double deltaTime) {
        position = position.add(0, speedY * deltaTime);

        shootTimer += deltaTime;
        if (shootTimer >= shootCooldown) {
            shootTimer = 0;
            shootAtPlayer();
        }

        if (position.getY() > gameScene.getSize().getHeight() + height) {
            alive = false;
        }
    }

    private void shootAtPlayer() {
        Point2D playerPos = gameScene.getPlayer().getPosition();
        Point2D direction = playerPos.subtract(position).normalize().multiply(250);
        gameScene.addBullet(new EnemyBullet(position, direction));
    }
}
