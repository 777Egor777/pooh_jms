package ru.job4j.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public class Client implements Runnable {
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 9001)) {
            try (PrintStream output = new PrintStream(
                    socket.getOutputStream(),
                    true
            )) {
                output.println("XXX");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread serverThread = new Thread(new Server());
        Thread clientThread = new Thread(new Client());
        serverThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientThread.start();
    }
}
