package lab;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.stage.WindowEvent;

public class App extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    private Canvas canvas;
    private AnimationTimer timer;

    private int w = 768;
    private int h = 700;

    private final URL cssUrl = getClass().getResource("application.css");

    @Override
    public void start(Stage stage) throws Exception {
        if (timer != null) {
            timer.stop();
            timer = null;
        }

        this.stage = stage;


        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();

        MainMenuController controller = loader.getController();
        controller.setMainApp(this);


        Scene scene = new Scene(root, w, h);
        scene.getStylesheets().add(Objects.requireNonNull(cssUrl).toString());
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    public void startGame(Stage primaryStage) {
        try {
            if (timer != null) {
                timer.stop();
                timer = null;
            }
            //Construct a main window with a canvas.
            Group root = new Group();
            canvas = new Canvas(w, h);
            root.getChildren().add(canvas);
            Scene scene = new Scene(root, w, h);
            this.stage = primaryStage;

            Set<KeyCode> keysPressed = new HashSet<>();
            scene.setOnKeyPressed(event -> keysPressed.add(event.getCode()));
            scene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));

            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().set(false);
            primaryStage.setTitle("1942");
            primaryStage.show();
            //Exit program when main window is closed
            primaryStage.setOnCloseRequest(this::exitProgram);
            timer = new DrawingThread(canvas, keysPressed, this);
            timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameOverMenu(int score) {
        try {
            if (timer != null) {
                timer.stop();
                timer = null;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOverMenu.fxml"));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setMainApp(this);
            controller.setScore(score);

            Scene scene = new Scene(root, w, h);
            scene.getStylesheets().add(Objects.requireNonNull(cssUrl).toString());
            stage.setScene(scene);
            stage.setTitle("Game Over");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToMainMenu() {
        System.out.println("Switching to main menu");
        try {
            if (timer != null) {
                timer.stop();
                timer = null;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent root = loader.load();

            MainMenuController controller = loader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(root, w, h);
            scene.getStylesheets().add(Objects.requireNonNull(cssUrl).toString());
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToScoreboard() {
        System.out.println("Switching to scoreboard");
        try {
            if (timer != null) {
                timer.stop();
                timer = null;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scoreboard.fxml"));
            Parent root = loader.load();

            ScoreBoardController controller = loader.getController();
            controller.setMainApp(this);

            controller.loadScores(ScoreManager.loadScores());

            Scene scene = new Scene(root, w, h);
            stage.setScene(scene);
            stage.setTitle("Scoreboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void stop() throws Exception {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        super.stop();
    }

    public void quitGame() {
        if (timer != null) {
            timer.stop();
        }
        Platform.exit();
        System.exit(0);
    }

    private void exitProgram(WindowEvent evt) {
        System.exit(0);
    }
}
