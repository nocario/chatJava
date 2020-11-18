package console;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;

public class WriteThread extends Thread {
    private final BufferedWriter out;
    private final String username;

    WriteThread(Socket socket, String username) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.username = username;
    }

    public void run() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String msg = "["+username +"]: "+ sc.nextLine();
                out.write(msg);
                out.write("\r\n");
                out.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMessage(String msg) throws IOException {
        msg = LocalTime.now().toString().split("\\.")[0] + " " + "["+username +"]:" + msg;
        out.write(msg);
        out.write("\r\n");
        out.flush();
    }
}