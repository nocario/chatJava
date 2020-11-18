package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {

    private final BufferedReader in;

    private String msg;

    ReadThread(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.readLine().trim();
                System.out.println(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}