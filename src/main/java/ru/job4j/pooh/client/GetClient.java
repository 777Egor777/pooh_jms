package ru.job4j.pooh.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public class GetClient implements Callable<String> {
    private final int port;
    private final String request;

    public GetClient(int port, String request) {
        this.port = port;
        this.request = request;
    }

    @Override
    public String call() {
        String msg = "";
        try (Socket socket = new Socket("localhost", port)) {
            try (PrintStream output = new PrintStream(
                    socket.getOutputStream(),
                    true
                 );
                 BufferedReader input = new BufferedReader(
                         new InputStreamReader(
                                 socket.getInputStream()
                         )
                 )
                ) {
                output.println(this.request);
                msg = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
