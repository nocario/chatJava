package Ui;

import console.ReadThread;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chatUI.fxml"));
        primaryStage.setTitle("Chatty");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void initialize() throws Exception {
        client = new console.Client(this);

        buttonSend.setOnAction((e) -> {
            try {
                sendMessage();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

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
