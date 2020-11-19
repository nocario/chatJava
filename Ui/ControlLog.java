package Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControlLog {
    @FXML
    private Button confButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField ipField;

    private static String username = "";

    private static String ipAdresse = "";

    @FXML
    private void initialize() {
        cancelButton.setOnAction((e) -> StageStock.stage.hide());
        confButton.setOnAction((e) -> {
            username = usernameField.getText();
            ipAdresse = ipField.getText();
            StageStock.stage.hide();
        });
    }

    public static String getUsername() {
        return username;
    }

    public static String getIpAdresse() {
        return ipAdresse;
    }
}
