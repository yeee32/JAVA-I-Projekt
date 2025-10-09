package lab;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameScene {

    private static final Random RANDOM = new Random();

    private Dimension2D size;
    private Player player;
    private Enemy enemy;

    private List<Bullet> bullets = new ArrayList<>();

    private List<Enemy> enemies = new ArrayList<>();

    private Set<KeyCode> keysPressed;

    public GameScene(double width, double height, Set<KeyCode> keysPressed) {
        size = new Dimension2D(width, height);
        this.keysPressed = keysPressed;
        double centerX = width / 2;
        double centerY = height / 2;
        int enemyCount = 10;
        player = new Player(this, new Point2D(centerX, centerY));
        for(int i = 0; i < enemyCount; i++){
            enemies.add(
                new Enemy(this,
                new Point2D(RANDOM.nextDouble(0, size.getWidth()),
                RANDOM.nextDouble(0,size.getHeight()))
            )
            );
        }
    }
    public void draw(GraphicsContext gc) {
        gc.save();
        player.draw(gc);

        for(Enemy enemy : enemies){
            enemy.draw(gc);
        }
        for(Bullet bullet : bullets){
            bullet.draw(gc);
        }


        gc.restore();
    }
    public void simulate(double delay){
        if (keysPressed.contains(KeyCode.W)){
            player.moveUp(delay);
        }
        if (keysPressed.contains(KeyCode.S)){
            player.moveDown(delay);
        }
        if (keysPressed.contains(KeyCode.A)){
            player.moveLeft(delay);
        }
        if (keysPressed.contains(KeyCode.D)){
            player.moveRight(delay);
        }
        if (keysPressed.contains(KeyCode.J)){
            player.shoot();
        }

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = (Bullet)bulletIterator.next();
            bullet.simulate(delay);
            if(bullet.getPosition().getY() < 0){
                bulletIterator.remove();
                System.out.println("bullet removed");
            }
        }

        if(player.getPosition().getX() < 0 + player.width / 2){
            player.setPosition(new Point2D(0 + player.width / 2, player.getPosition().getY()));
        }

        if(player.getPosition().getX() > size.getWidth() - player.width / 2){
            player.setPosition(new Point2D(size.getWidth() - player.width / 2, player.getPosition().getY()));
        }

        if(player.getPosition().getY() < 0 + player.height / 2){
            player.setPosition(new Point2D(player.getPosition().getX(), 0 + player.height / 2));
        }

        if(player.getPosition().getY() > size.getHeight() - player.height / 2){
            player.setPosition(new Point2D(player.getPosition().getX(), size.getHeight() - player.height / 2));
        }


        for(Enemy enemy : enemies){
            if(player.getHitbox().intersects(enemy.getHitbox())){
                System.out.println("enemy hit player - game over");
            }
        }

        for(Bullet bullet : bullets){
            for(Enemy enemy : enemies){
                if(bullet.getHitbox().intersects(enemy.getHitbox())){
                    System.out.println("bullet hit enemy");
                    enemy.setColor();
                }
            }
        }

    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

}
