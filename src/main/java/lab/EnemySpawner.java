package lab;

import javafx.geometry.Point2D;
import java.util.Random;

public class EnemySpawner {

    private final GameScene gameScene;
    private final Random random = new Random();

    private double spawnTimer = 0;
    private double spawnInterval = 2.0;

    private double difficultyTimer = 0;
    private int difficulty = 1;

    public EnemySpawner(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public void update(double deltaTime) {
        spawnTimer += deltaTime;
        difficultyTimer += deltaTime;

        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0;
            spawnWave();
        }

        if (difficultyTimer >= 15) {
            difficultyTimer = 0;
            increaseDifficulty();
        }
    }

    private void increaseDifficulty() {
        difficulty++;
        spawnInterval = Math.max(0.6, spawnInterval - 0.15);
    }

    private void spawnWave() {
        int waveType = random.nextInt(4);
        int count = Math.min(2 + difficulty, 8);

        switch (waveType) {
            case 0 -> spawnSmallFighters(count);
            case 1 -> spawnMediumBombers(Math.max(1, count / 2));
            case 2 -> spawnBouncePlanes(Math.max(1, count / 2));
            case 3 -> mixedWave(count);
        }
    }

    private void spawnSmallFighters(int count) {
        double spacing = gameScene.getSize().getWidth() / (count + 1);

        for (int i = 0; i < count; i++) {
            gameScene.addEnemy(
                new SmallFighter(
                    gameScene,
                    new Point2D(spacing * (i + 1), -30)
                )
            );
        }
    }

    private void spawnMediumBombers(int count) {
        double centerX = gameScene.getSize().getWidth() / 2;

        for (int i = 0; i < count; i++) {
            double offset = (i - count / 2.0) * 80;
            gameScene.addEnemy(
                new MediumBomber(
                    gameScene,
                    new Point2D(centerX + offset, -60 - Math.abs(offset))
                )
            );
        }
    }

    private void spawnBouncePlanes(int count) {
        for (int i = 0; i < count; i++) {
            gameScene.addEnemy(
                new BouncePlane(
                    gameScene,
                    new Point2D(
                        random.nextDouble(50, gameScene.getSize().getWidth() - 50),
                        -40 * i
                    )
                )
            );
        }
    }

    private void mixedWave(int count) {
        spawnSmallFighters(count / 2);
        spawnMediumBombers(1 + difficulty / 3);
    }
}
