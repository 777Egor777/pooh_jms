package ru.job4j.socket;

import ru.job4j.pooh.model.Request;
import ru.job4j.pooh.server.consumer.QueueConsumer;
import ru.job4j.pooh.server.consumer.TopicConsumer;
import ru.job4j.pooh.server.producer.QueueProducer;
import ru.job4j.pooh.server.producer.TopicProducer;
import ru.job4j.pooh.util.ThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Egor Geraskin(yegeraskin13@gmail.com)
 * @version 1.0
 * @since 15.01.2021
 */
public class Server implements Runnable {
    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(9001)) {
            System.out.printf("Server(port: %d) started!" + System.lineSeparator(), 9001);
            while (true) {
                System.out.println("Wait for socket!");
                Socket socket = server.accept();
                System.out.println("Get Socket!");
                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    System.out.println(input.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
