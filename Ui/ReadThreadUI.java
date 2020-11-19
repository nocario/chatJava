package Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThreadUI extends Thread {

    private final InitApp app;
    private final BufferedReader in;

    public ReadThreadUI(Socket client, InitApp app) throws IOException {
        this.app = app;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.readLine().trim();
                System.out.println(msg);
                app.getChatTextArea().appendText("\n" + msg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}