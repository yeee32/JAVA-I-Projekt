package lab;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;


public class BouncePlane extends EnemyEntity implements HasCollision {
    private static final Random RANDOM = new Random();
    private double speed;

    private double shootTimer = 0;
    private double shootCooldown = 1.75;

    double width = 45;
    double height = 45;

    private static Image downImg;
    private static Image upImg;


    public BouncePlane(GameScene gameScene, Point2D position) {
        super(gameScene, position, 25, 25);
        this.speed = 225;
        loadImages();
    }

    private static void loadImages() {
        if (downImg == null) {
            downImg = new Image(
                Player.class.getResourceAsStream("xaki_down.png")
            );
            upImg = new Image(
                Player.class.getResourceAsStream("xaki_up.png")
            );
        }
    }


    private double randX = RANDOM.nextDouble(-100,100);
    // random limit when the small fighter turns around
    private double lim = gameScene.getSize().getWidth() * RANDOM.nextDouble(0.6, 0.9);



    public void simulate(double delay){
        position = position.add(randX * delay, speed * delay);
        if (position.getY() > lim) {
            speed = -speed;
        }

        shootTimer += delay;
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

    public void draw(GraphicsContext gc) {
        gc.save();
        Image toDraw = (speed >= 0) ? downImg : upImg;
        gc.drawImage(toDraw, position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }


}
