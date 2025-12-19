package lab;

import javafx.scene.control.Label;

public class Score {
    private GameScene gameScene;
    private int score;

    private Label scoreLabel;

    public Score(GameScene gameScene) {
        this.gameScene = gameScene;
        this.score = 0;
        scoreLabel = new Label("score:" + 0);
        scoreLabel.setStyle(
            "-fx-font-size: 24px;" +
                "-fx-text-fill: white;"
        );
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        this.score += points;
        scoreLabel.setText("score:" + score);
    }

}
