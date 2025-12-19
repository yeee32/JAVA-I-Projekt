package lab;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button start_button;

    @FXML
    private Button quit_button;

    private App app;

    @FXML
    void initialize() {
        assert quit_button != null : "fx:id=\"quit_button\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert start_button != null : "fx:id=\"start_button\" was not injected: check your FXML file 'mainMenu.fxml'.";
    }

    @FXML
    private void startGame() {
        try {
            // start game
            App game = new App();
            Stage stage = (Stage) start_button.getScene().getWindow();
            game.startGame(stage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitGame() {
        app.quitGame();
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
