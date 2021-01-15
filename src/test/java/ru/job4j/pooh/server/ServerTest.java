package ru.job4j.pooh.server;

import org.junit.Test;
import ru.job4j.pooh.client.GetClient;
import ru.job4j.pooh.client.PostClient;
import ru.job4j.pooh.queue.GeneralQueue;
import ru.job4j.pooh.util.ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void testQueue() {
        String json = "{ "
                + "\"queue\": \"" + "weather\"; "
                + "\"text\": \"" + "+18 C\" }";
        String request = "POST / QUEUE / weather / " + json;
        Server server1 = new Server(9000);
        PostClient post = new PostClient(9000, request);
        Server server2 = new Server(9002);
        GetClient get = new GetClient(9002, "GET / QUEUE / weather");
        new Thread(server1).start();
        Thread postThread = new Thread(post);
        postThread.start();
        try {
            postThread.join();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(server2).start();
        Future<String> future =  ThreadPool.instOf().submit(get);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assertThat(result, is(json));
    }
}