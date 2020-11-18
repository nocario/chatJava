package console;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
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
