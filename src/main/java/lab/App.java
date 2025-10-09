package lab;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;
import javafx.stage.WindowEvent;
import javafx.scene.input.KeyCode;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Canvas canvas;
    private AnimationTimer timer;


    private int w = 1000;
    private int h = 700;

    @Override
    public void start(Stage primaryStage) {
        try {
            //Construct a main window with a canvas.
            Group root = new Group();
            canvas = new Canvas(w, h);
            root.getChildren().add(canvas);
            Scene scene = new Scene(root, w, h);

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
            timer = new DrawingThread(canvas, keysPressed);
            timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        timer.stop();
        super.stop();
    }

    private void exitProgram(WindowEvent evt) {
        System.exit(0);
    }
}
