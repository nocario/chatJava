package console;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
    private static final int host = 24318;

    private static Ui.ReadThreadUI read;
    private static WriteThread write;
    private Ui.InitApp app;

    /*public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("192.168.1.104");
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String user = sc.next();
        System.out.println("connecting...");
        Socket client = new Socket(ip, host);
        System.out.println("Connection established.");

        read = new ReadThread(client);
        write = new WriteThread(client, user);

        read.start();
        write.start();

    }*/

    public Client(Ui.InitApp app, String username, String ipAdresse) throws Exception {
        this.app = app;
        InetAddress ip = InetAddress.getByName(ipAdresse);
        String user = username;
        System.out.println("connecting...");
        Socket client = new Socket(ip, host);
        System.out.println("Connection established.");

        read = new Ui.ReadThreadUI(client, app);
        write = new WriteThread(client, user);

        read.start();
        //write.start();
    }
    public WriteThread getWrite() {
        return write;
    }

}
