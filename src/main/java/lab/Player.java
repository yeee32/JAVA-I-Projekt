package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player implements HasCollision{
    private GameScene gameScene;
    private Point2D position;
    private GraphicsContext gc;
    private Canvas canvas;
    private Bullet bullet;

    private static Image image;

    double width = 60;
    double height = 60;

    double bulletSpeed = 400;
    private boolean powUpActive = false;
    private Point2D velocity = new Point2D(0, -bulletSpeed);

    private double bulletCooldown = 0.15; // seconds between shots
    private double lastShotTimer = 0;

    private boolean invincible = false;
    private double invincibleDuration = 3; // seconds
    private double invincibleTimer = 0;

    private double invincibleCooldown = 4;
    private double invincibleCooldownTimer = 0;

    private static Image defaultImage;
    private static Image invincibleImage;

    public Player(GameScene gameScene, Point2D position){
        this.gameScene = gameScene;
        this.position = position;
        loadImages();
    }

    private static void loadImages() {
        if (defaultImage == null) {
            defaultImage = new Image(
                Player.class.getResourceAsStream("player_plane.png")
            );
            invincibleImage = new Image(
                Player.class.getResourceAsStream("player_plane_shielded.png")
            );
        }
    }

    public void draw(GraphicsContext gc){
        gc.save();

        Image image = invincible ? invincibleImage : defaultImage;

        gc.drawImage(image, position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }

    double movementSpeed = 300;

    public void simulate(double delay){
        lastShotTimer += delay;
        if (invincible) {
            invincibleTimer += delay;
            if (invincibleTimer >= invincibleDuration) {
                invincible = false;
                invincibleTimer = 0;
            }
        }
        else {
            invincibleCooldownTimer += delay;
        }
    }

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

    public void dodge(){
        if (!invincible && invincibleCooldownTimer >= invincibleCooldown) {
            invincible = true;
            invincibleCooldownTimer = 0;
            System.out.println("Player dodged");
        }
    }

    public void shoot(){
        if (lastShotTimer >= bulletCooldown) {
            Point2D playerCenter = position.add(0, -30);

            if(powUpActive) {
                // triple shot
                spawnBullet(playerCenter, new Point2D(-100, -bulletSpeed)); // left bullet
                spawnBullet(playerCenter, new Point2D(0, -bulletSpeed)); // center bullet
                spawnBullet(playerCenter, new Point2D(100, -bulletSpeed)); // right bullet
            }
            else{
                spawnBullet(playerCenter, new Point2D(0, -bulletSpeed));
            }

            lastShotTimer = 0; // reset timer
        }
    }

    public void takeDamage() {
        if (invincible) {
            return;
        }

        invincible = true;
        invincibleTimer = 0;
        invincibleCooldownTimer = 0;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void spawnBullet(Point2D position, Point2D velocity) {
        PlayerBullet bullet = new PlayerBullet(position, velocity);
        gameScene.addBullet(bullet);
    }

    public void activatePowerUp(PowerUp powerUp){
        if(!powUpActive){
            System.out.println("PowerUp activated");
            powUpActive = true;
        }
        else{
            System.out.println("PowerUp already active");
            gameScene.addScore(1000);
        }
    }

    public void disablePowerUp(PowerUp powerUp){
        if(powUpActive){
            System.out.println("PowerUp disabled");
            powUpActive = false;
        }
    }

    public Rectangle2D getHitbox(){
        return new Rectangle2D(
            position.getX() - width / 2, position.getY() - height / 2, width, height
        );
    }

    public boolean isInvincible() {
        return invincible;
    }
}
