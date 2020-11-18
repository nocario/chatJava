package console;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class WriteThread extends Thread {
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
}

class ReadThread extends Thread {

    private final BufferedReader in;

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

public class Client {
    private static final int host = 24318;
    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("192.168.1.104");
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String user = sc.next();
        System.out.println("connecting...");
        Socket client = new Socket(ip, host);
        System.out.println("Connection established.");

        ReadThread read = new ReadThread(client);
        WriteThread write = new WriteThread(client, user);

        read.start();
        write.start();

    }
}
