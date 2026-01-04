package lab;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Comparator;
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

    private List<PowerUp> powerUps = new ArrayList<>();

    private List<Explosion> explosions = new ArrayList<>();

    private Set<KeyCode> keysPressed;

    private Comparator<EnemyEntity> comparator;

    private EnemySpawner infEnemySpawner;


    private ScoreText scoreObj;
    private PlayerLives livesObj;
    private final int pointsAmmount = 100;
    private final int padding = 50;

    private boolean gameOver = false;

    private static final double POWERUP_SPAWN_CHANCE = 0.1;

    public GameScene(double width, double height, Set<KeyCode> keysPressed) {
        size = new Dimension2D(width, height);
        this.keysPressed = keysPressed;
        double centerX = width / 2;
        double centerY = height / 2;
        background = new Background(this);
        player = new Player(this, new Point2D(centerX, centerY));
        infEnemySpawner = new EnemySpawner(this);
        gameOver = false;
        scoreObj = new ScoreText(this);
        livesObj = new PlayerLives(this);
        comparator = new Comparator<EnemyEntity>() {
            @Override
            public int compare(EnemyEntity o1, EnemyEntity o2) {
                if (o1 instanceof SmallFighter && o2 instanceof MediumBomber) {
                    return 1;
                }
                if (o1 instanceof MediumBomber && o2 instanceof SmallFighter) {
                    return -1;
                }
                return 0;
            }
        };

    }

    public void addEnemy(EnemyEntity enemy) {
        enemies.add(enemy);
        enemies.sort(comparator);
    }

    public Point2D randomEnemySpawnPosition(){
        return new Point2D(RANDOM.nextDouble(10, size.getWidth() - 10), -10);
    }

    public Dimension2D getSize() {
        return size;
    }

    // draws everything
    public void draw(GraphicsContext gc) {
        gc.save();
        background.draw(gc);


        for (EnemyEntity enemy : enemies) {
            enemy.draw(gc);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(gc);
        }

        for (Explosion explosion : explosions) {
            explosion.draw(gc);
        }

        for (PowerUp pu : powerUps) {
            pu.draw(gc);
        }
        player.draw(gc);

        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("Arial", 24));
        gc.fillText("Score: " + scoreObj.getScore(), 20, 30);

        gc.fillText("Lives: " + livesObj.getLives(), size.getWidth() - 120, 30);
        gc.restore();
    }

    public void simulate(double delay) {
        infEnemySpawner.update(delay);
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

        if (keysPressed.contains(KeyCode.K)) {
            player.dodge();
        }

        // keeps player inside bounds
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


        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.simulate(delay);

            // Remove bullets if off-screen
            if (bullet.getPosition().getY() < -50 || bullet.getPosition().getY() > size.getHeight() + 50) {
                bulletIterator.remove();
                continue;
            }

            // PlayerBullet hits enemies
            if (bullet instanceof PlayerBullet) {
                Iterator<EnemyEntity> enemyIterator = enemies.iterator();
                while (enemyIterator.hasNext()) {
                    EnemyEntity enemy = enemyIterator.next();
                    if (bullet.getHitbox().intersects(enemy.getHitbox())) {

                        addExplosion(enemy.getPosition());

                        if (RANDOM.nextDouble() < POWERUP_SPAWN_CHANCE) {
                            spawnPowerUp(enemy.getPosition());
                        }

                        enemyIterator.remove();
                        bulletIterator.remove();
                        scoreObj.addPoints(pointsAmmount);
                        break;
                    }
                }
            }

            // EnemyBullet hits player
            if (bullet instanceof EnemyBullet) {
                if (bullet.getHitbox().intersects(player.getHitbox()) && !player.isInvincible()) {
                    bulletIterator.remove();
                    if(livesObj.getLives() <= 1){
                        gameOver = true;
                        System.out.println("Game Over");
                    }
                    livesObj.loseLife();
                    player.takeDamage();
                    player.disablePowerUp(powerUp);
                    break;
                }
            }
        }

        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            powerUp.simulate(delay);
            if (player.collidesWith(powerUp.getHitbox())) {
                player.activatePowerUp(powerUp);
                powerUpIterator.remove();
            }
        }

        Iterator<Explosion> explosionIterator = explosions.iterator();
        while (explosionIterator.hasNext()) {
            Explosion explosion = explosionIterator.next();
            explosion.simulate(delay);

            if (explosion.isFinished()) {
                explosionIterator.remove();
            }
        }

        Iterator<EnemyEntity> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            EnemyEntity enemy = enemyIterator.next();
            enemy.simulate(delay);
            if (enemy.collidesWith(player.getHitbox()) && !player.isInvincible()) {
                if(livesObj.getLives() <= 1){
                    gameOver = true;
                    System.out.println("Game Over");
                }
                livesObj.loseLife();
                player.takeDamage();
                player.disablePowerUp(powerUp);
                break;
            }

            if (enemy.getPosition().getY() < -padding * 3 || enemy.getPosition().getY() > size.getHeight() + padding || enemy.getPosition().getX() < -padding || enemy.getPosition().getX() > size.getWidth() + padding) {
                enemyIterator.remove();
                System.out.println("Enemy removed for going off-screen");
            }
        }
    }
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void spawnPowerUp(Point2D position) {
        powerUps.add(new PowerUp(this, position));
    }

    public void addScore(int points) {
        scoreObj.addPoints(points);
    }

    public int getScore(){
        return scoreObj.getScore();
    }

    public Player getPlayer() {
        return player;
    }

    public void addExplosion(Point2D position) {
        explosions.add(new Explosion(position));
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
