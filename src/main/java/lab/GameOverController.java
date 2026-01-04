package lab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameOverController {

    @FXML
    private Button main_menu_button;

    @FXML
    private URL location;

    @FXML
    private TextField input_text;

    @FXML
    private Button quit_button;

    @FXML
    private Button restart_button;

    private App app;
    private Stage stage;
    private int score;

    @FXML
    private Label score_label;

    @FXML
    private Label score_text_label;

    private boolean scoreSaved = false;

    private void saveOnce() {
        if (!scoreSaved) {
            ScoreManager.saveScore(input_text.getText().trim(), score);
            scoreSaved = true;
        }
    }

    public void setScore(int score){
        this.score = score;
        score_label.setText(String.valueOf(score));
    }


    @FXML
    void quitGame(ActionEvent event) {
        saveOnce();
        app.quitGame();
    }

    @FXML
    void goToMainMenu(ActionEvent event){
        saveOnce();
        app.switchToMainMenu();
    }


    @FXML
    void startGame(ActionEvent event) {
        saveOnce();
        Stage stage = (Stage) restart_button.getScene().getWindow();
        app.startGame(stage);
    }

    @FXML
    void initialize() {
        assert input_text != null : "fx:id=\"input_text\" was not injected: check your FXML file 'gameOverMenu.fxml'.";
        assert main_menu_button != null : "fx:id=\"main_menu_button\" was not injected: check your FXML file 'gameOverMenu.fxml'.";
        assert quit_button != null : "fx:id=\"quit_button\" was not injected: check your FXML file 'gameOverMenu.fxml'.";
        assert restart_button != null : "fx:id=\"restart_button\" was not injected: check your FXML file 'gameOverMenu.fxml'.";
        assert score_label != null : "fx:id=\"score_label\" was not injected: check your FXML file 'gameOverMenu.fxml'.";
        assert score_text_label != null : "fx:id=\"score_text_label\" was not injected: check your FXML file 'gameOverMenu.fxml'.";
    }

    public void setMainApp(App app) {
        this.app = app;
    }

}
