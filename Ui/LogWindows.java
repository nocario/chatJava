package Ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class StageStock {
    public static Stage stage = new Stage();
}

public class LogWindows extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("logUI.fxml"));
        stage.setTitle("Log");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.toFront();
        stage.show();
    }
}
