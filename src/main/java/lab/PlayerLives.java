package lab;

import javafx.scene.control.Label;

public class PlayerLives {
    private GameScene gameScene;
    private int lives;

    private Label livesLabel;

    PlayerLives(GameScene gameScene) {
        this.gameScene = gameScene;
        this.lives = 3;
        livesLabel = new Label("lives:" + 0);
        livesLabel.setStyle(
            "-fx-font-size: 24px;" + "-fx-text-fill: white;"
        );
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        this.lives -= 1;
        livesLabel.setText("lives:" + lives);
    }
}

