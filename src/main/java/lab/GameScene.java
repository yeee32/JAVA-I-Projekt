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
    private PowerUp powerUp;
    private Background background;

    private List<Bullet> bullets = new ArrayList<>();
    private List<EnemyEntity> enemies = new ArrayList<>();

    private Set<KeyCode> keysPressed;

    public GameScene(double width, double height, Set<KeyCode> keysPressed) {
        size = new Dimension2D(width, height);
        this.keysPressed = keysPressed;
        double centerX = width / 2;
        double centerY = height / 2;
        int enemyCount = 50;
        background = new Background(this);
        player = new Player(this, new Point2D(centerX, centerY));
        powerUp = new PowerUp(this, new Point2D(300, 300));
        for (int i = 0; i < enemyCount; i++) {
            if (RANDOM.nextBoolean()) {
                enemies.add(new SmallFighter(this, randomPosition()));
            }
            else {
                enemies.add(new MediumBomber(this, randomPosition()));
            }
        }
    }

    public Point2D randomPosition(){
        return new Point2D(RANDOM.nextDouble(0, size.getWidth()),
            RANDOM.nextDouble(0, size.getHeight()));
    }

    public Dimension2D getSize() {
        return size;
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        background.draw(gc);
        powerUp.draw(gc);
        player.draw(gc);

        for (EnemyEntity enemy : enemies) {
            enemy.draw(gc);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(gc);
        }

        gc.restore();
    }

    public void simulate(double delay) {
        background.simulate(delay);
        player.simulate(delay);
        if (keysPressed.contains(KeyCode.W)) {
            player.moveUp(delay);
        }
        if (keysPressed.contains(KeyCode.S)) {
            player.moveDown(delay);
        }
        if (keysPressed.contains(KeyCode.A)) {
            player.moveLeft(delay);
        }
        if (keysPressed.contains(KeyCode.D)) {
            player.moveRight(delay);
        }
        if (keysPressed.contains(KeyCode.J)) {
            player.shoot();
        }

        if (player.getPosition().getX() < 0 + player.width / 2) {
            player.setPosition(new Point2D(0 + player.width / 2, player.getPosition().getY()));
        }

        if (player.getPosition().getX() > size.getWidth() - player.width / 2) {
            player.setPosition(new Point2D(size.getWidth() - player.width / 2, player.getPosition().getY()));
        }

        if (player.getPosition().getY() < 0 + player.height / 2) {
            player.setPosition(new Point2D(player.getPosition().getX(), 0 + player.height / 2));
        }

        if (player.getPosition().getY() > size.getHeight() - player.height / 2) {
            player.setPosition(new Point2D(player.getPosition().getX(), size.getHeight() - player.height / 2));
        }

        if (player.getHitbox().intersects(powerUp.getHitbox())) {
            System.out.println("player collected powerup");
        }

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.simulate(delay);
            if (bullet.getPosition().getY() < 0) {
                bulletIterator.remove();
                System.out.println("bullet removed - out of bounds");
            }
        }
        Iterator<EnemyEntity> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            EnemyEntity enemy = enemyIterator.next();
            enemy.simulate(delay);

            if (enemy.getHitbox().intersects(player.getHitbox())) {
                System.out.println("enemy hit player");
            }

            for (Bullet bullet : bullets) {
                if (bullet.getHitbox().intersects(enemy.getHitbox())) {
                    System.out.println("bullet hit enemy");
                    bullets.remove(bullet);
                    enemyIterator.remove();
                    break;
                }
            }
        }
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

}
