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

    private double bulletCooldown = 0.15; // seconds between shots
    private double lastShotTimer = 0;

    public Player(GameScene gameScene, Point2D position){
        this.gameScene = gameScene;
        this.position = position;
        image = getImg();
    }

    private static Image getImg(){
        if (image == null) {
            image = new Image(Player.class.getResourceAsStream("player_plane.png"));
        }
        return  image;
    }

    public void draw(GraphicsContext gc){
        gc.save();
        gc.drawImage(image, position.getX() - width / 2, position.getY() - height / 2, width, height);
        gc.restore();
    }

    double movementSpeed = 200;

    public void simulate(double delay){
        lastShotTimer += delay;
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

    public void shoot(){
        if (lastShotTimer >= bulletCooldown) {
            Bullet bullet = new Bullet(gameScene, position);
            gameScene.addBullet(bullet);
            lastShotTimer = 0; // reset timer
        }
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
