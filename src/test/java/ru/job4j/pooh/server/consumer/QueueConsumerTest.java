package ru.job4j.pooh.server.consumer;

import org.junit.Test;
import ru.job4j.pooh.queue.GeneralQueue;
import ru.job4j.pooh.util.ThreadPool;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueueConsumerTest {

    @Test
    public void call() {
        GeneralQueue.instOf().offer("a", "b");
        QueueConsumer consumer = new QueueConsumer("a");
        String result = "";
        try {
            result = ThreadPool.instOf().submit(consumer).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assertThat(result, is("b"));
    }
}