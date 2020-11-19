package Ui;

import console.Client;
import console.ReadThread;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InitApp extends Application {
    
    @FXML
    private TextArea msgTextArea;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private Button buttonSend;

    private console.Client client;

    private LogWindows log;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        log = new LogWindows();
        log.start(StageStock.stage);
        Parent root = FXMLLoader.load(getClass().getResource("chatUI.fxml"));
        primaryStage.setTitle("Chatty");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        StageStock.stage.setAlwaysOnTop(true);
    }

    @FXML
    private void initialize() throws Exception {

        StageStock.stage.setOnHidden((e) -> {
            if (ControlLog.getIpAdresse().length() > 0 && ControlLog.getUsername().length() > 0) {
                try {
                    client = new Client(this, ControlLog.getUsername(), ControlLog.getIpAdresse());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else {
                System.exit(-1);
            }
        });

        buttonSend.setOnAction((e) -> {
            try {
                sendMessage();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        msgTextArea.textProperty().addListener(((observableValue, s, t1) -> {
            if (t1.length()%30 == 0) {
                msgTextArea.appendText("\n");
            }
        }));

    }

    private void sendMessage() throws IOException {
        client.getWrite().sendMessage(msgTextArea.getText().trim());
        msgTextArea.setText("");
    }

    public TextArea getChatTextArea() {
        return chatTextArea;
    }
    public console.Client getClient() {
        return client;
    }
}
