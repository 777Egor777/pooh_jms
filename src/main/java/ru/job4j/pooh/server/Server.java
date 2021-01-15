package ru.job4j.pooh.server;

import ru.job4j.pooh.model.Request;
import ru.job4j.pooh.queue.Queue;
import ru.job4j.pooh.server.consumer.QueueConsumer;
import ru.job4j.pooh.server.consumer.TopicConsumer;
import ru.job4j.pooh.server.producer.QueueProducer;
import ru.job4j.pooh.server.producer.TopicProducer;
import ru.job4j.pooh.util.ParseRequest;
import ru.job4j.pooh.util.ThreadPool;
import ru.job4j.pooh.util.Topics;

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
    private final static ParseRequest PARSER = ParseRequest.instOf();
    private final int port;
    private final Queue queue = new Queue();

    public Server(int port) {
        this.port = port;
        Topics.instOf().addTopicQueue(queue);
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.printf("Server(port: %d) started!" + System.lineSeparator(), port);
            while (true) {
                System.out.println("Wait for Socket!");
                Socket socket = server.accept();
                System.out.println("Get Socket!");
                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintStream output = new PrintStream(socket.getOutputStream(), true)) {
                    String line = input.readLine();
                    Request req = PARSER.parse(line);
                    if (req.getMethod() == Request.METHOD_POST) {
                        if (req.getMode() == Request.MODE_QUEUE) {
                            ThreadPool.instOf().execute(new QueueProducer(
                                    req.getTheme(),
                                    req.getJson()
                            ));
                        } else {
                            ThreadPool.instOf().execute(new TopicProducer(
                                    req.getTheme(),
                                    req.getJson()
                            ));
                        }
                    } else {
                        if (req.getMode() == Request.MODE_QUEUE) {
                            output.println(
                                    ThreadPool.instOf().submit(
                                        new QueueConsumer(req.getTheme())
                                    ).get()
                            );
                        } else {
                            output.println(
                                    ThreadPool.instOf().submit(
                                            new TopicConsumer(req.getTheme(), queue)
                                    ).get()
                            );
                        }
                    }


                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
