package ru.job4j.pooh.client;

import org.junit.Test;
import ru.job4j.pooh.queue.GeneralQueue;
import ru.job4j.pooh.server.Server;
import ru.job4j.pooh.util.ThreadPool;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GetClientTest {

    @Test
    public void call() {
        Server server = new Server(9001);
        GeneralQueue.instOf().offer("a", "b");
        new Thread(server).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GetClient client = new GetClient(9001, "GET / QUEUE / a");
        String result = "";
        try {
            result = ThreadPool.instOf().submit(client).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(result, is("b"));
    }
}