package lab;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScoreBoardController {

    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button main_menu_button;

    @FXML
    private TableColumn<Score, String> name_collumn;

    @FXML
    private Button quit_button;

    @FXML
    private TableColumn<Score, Integer> score_collumn;

    @FXML
    private Label score_label;

    @FXML
    private TableView<Score> score_table;

    @FXML
    void goToMainMenu(ActionEvent event) {
        app.switchToMainMenu();
    }


    public void loadScores(List<Score> scores) {
        score_table.setItems(FXCollections.observableArrayList(scores));
    }

    @FXML
    void quitGame(ActionEvent event) {
        app.quitGame();
    }

    @FXML
    void initialize() {
        assert main_menu_button != null : "fx:id=\"main_menu_button\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert name_collumn != null : "fx:id=\"name_collumn\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert quit_button != null : "fx:id=\"quit_button\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert score_collumn != null : "fx:id=\"score_collumn\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert score_label != null : "fx:id=\"score_label\" was not injected: check your FXML file 'scoreboard.fxml'.";
        assert score_table != null : "fx:id=\"score_table\" was not injected: check your FXML file 'scoreboard.fxml'.";
        name_collumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        score_collumn.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
