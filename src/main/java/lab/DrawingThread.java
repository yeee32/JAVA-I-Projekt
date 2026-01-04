package lab;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;


public class DrawingThread extends AnimationTimer {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private GameScene gameScene;
    private long lastFrame = 0;

    private App app;


    public DrawingThread(Canvas canvas, Set<KeyCode> keysPressed, App app) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        gameScene = new GameScene(canvas.getWidth(), canvas.getHeight(), keysPressed);
        this.app = app;
    }

    @Override
    public void handle(long now) {
        int finalScore = gameScene.getScore();
        double delta = lastFrame == 0 ? 0 : (now - lastFrame) / 1_000_000_000D;
        lastFrame = now;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameScene.draw((gc));
        gameScene.simulate(delta);

        if (gameScene.isGameOver()) {
            stop();

            Platform.runLater(() -> app.gameOverMenu(finalScore));
        }
    }
}
