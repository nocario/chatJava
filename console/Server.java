package console;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


class UserThread extends Thread {

    private final BufferedWriter out;
    private final BufferedReader in;

    UserThread(Socket socket) throws IOException {
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.readLine().trim();
                System.out.println("Received: " + msg);
                Server.broadcast(msg);

            } catch (Exception e) {
                Server.killThread(this);
            }

        }
    }
    public void sendMessage(String msg) throws IOException {
        out.write(LocalTime.now().toString().split("\\.")[0] + " " +msg);
        out.write("\r\n");
        out.flush();
    }
}

public class Server {
    private static final int host = 24318;
    private static ServerSocket server;
    private static final Set<UserThread> userArray = new HashSet<>();

    public static void main(String[] args) {

        try {
            server = new ServerSocket(host, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server started");
        while (true) {
            try {
                Socket socket = server.accept();
                System.out.println("New user connected");
                UserThread user = new UserThread(socket);
                user.start();
                userArray.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void killThread(UserThread user) {
        userArray.remove(user);
        user.interrupt();
    }
    public static void broadcast(String msg) throws IOException {
        for (UserThread user : userArray) {
            user.sendMessage(msg);
        }
    }
}
