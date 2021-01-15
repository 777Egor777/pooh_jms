package ru.job4j.pooh.server.producer;

import org.junit.Test;
import ru.job4j.pooh.queue.GeneralQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueueProducerTest {

    @Test
    public void run() {
        Thread producer = new Thread(new QueueProducer("a", "b"));
        producer.start();
        try {
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(GeneralQueue.instOf().poll("a"), is("b"));
    }
}