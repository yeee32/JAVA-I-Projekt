package lab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SettingsContoller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button downButton;

    @FXML
    private Label downLabel;

    @FXML
    private Button leftButton;

    @FXML
    private Label leftLabel;

    @FXML
    private Button rightButton;

    @FXML
    private Label rightLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button upButton;

    @FXML
    private Label upLabel;

    private App app;

    @FXML
    void initialize() {
        assert downButton != null : "fx:id=\"downButton\" was not injected: check your FXML file 'settings.fxml'.";
        assert downLabel != null : "fx:id=\"downLabel\" was not injected: check your FXML file 'settings.fxml'.";
        assert leftButton != null : "fx:id=\"leftButton\" was not injected: check your FXML file 'settings.fxml'.";
        assert leftLabel != null : "fx:id=\"leftLabel\" was not injected: check your FXML file 'settings.fxml'.";
        assert rightButton != null : "fx:id=\"rightButton\" was not injected: check your FXML file 'settings.fxml'.";
        assert rightLabel != null : "fx:id=\"rightLabel\" was not injected: check your FXML file 'settings.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'settings.fxml'.";
        assert upButton != null : "fx:id=\"upButton\" was not injected: check your FXML file 'settings.fxml'.";
        assert upLabel != null : "fx:id=\"upLabel\" was not injected: check your FXML file 'settings.fxml'.";
        toDefaultLabels();
    }
    void setApp(App appp){
        this.app = appp;
    }

    private void toDefaultLabels(){

    }

}
