package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MediumBomber extends EnemyEntity implements HasCollision {
    private double shootTimer = 0;
    private double shootCooldown = 2; // seconds

    double width = 70;
    double height = 70;

    private static Image img;

    public MediumBomber(GameScene gameScene, Point2D position) {
        super(gameScene, position, 35, 35);
        this.speedY = 180;
        img = getImg();
    }

    Image getImg(){
        if (img == null) {
            img = new Image(SmallFighter.class.getResourceAsStream("blue_down.png"));
        }
        return img;
    }

    @Override
    public void simulate(double deltaTime) {
        position = position.add(0, speedY * deltaTime);

        shootTimer += deltaTime;
        if (shootTimer >= shootCooldown) {
            shootTimer = 0;
            shootAtPlayer();
        }

    }

    private void shootAtPlayer() {
        Point2D playerPos = gameScene.getPlayer().getPosition();
        Point2D direction = playerPos.subtract(position).normalize().multiply(250);
        gameScene.addBullet(new EnemyBullet(position, direction));
    }

    public void draw(GraphicsContext gc){
        gc.save();
        gc.drawImage(img, position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }

}
