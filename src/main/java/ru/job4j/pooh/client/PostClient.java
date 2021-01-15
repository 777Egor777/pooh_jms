package ru.job4j.pooh.client;

import ru.job4j.pooh.queue.GeneralQueue;
import ru.job4j.pooh.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public class PostClient implements Runnable {
    private final int port;
    private final String request;

    public PostClient(int port, String request) {
        this.port = port;
        this.request = request;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", port)) {
            try (PrintStream output = new PrintStream(
                         socket.getOutputStream(),
                         true
                 )) {
                output.print(this.request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread client = new Thread(new PostClient(9002, "POST / QUEUE / weather / abc"));
        client.start();
        Thread server = new Thread(new Server(9002));
        server.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(GeneralQueue.instOf().poll("weather"));
    }
}
